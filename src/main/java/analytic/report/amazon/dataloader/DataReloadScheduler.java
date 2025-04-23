package analytic.report.amazon.dataloader;

import analytic.report.amazon.dto.statistics.ReportSpecificationResponseDto;
import analytic.report.amazon.mapper.ReportSpecificationMapper;
import analytic.report.amazon.model.ReportData;
import analytic.report.amazon.repository.ReportSpecificationRepository;
import analytic.report.amazon.repository.SalesAndTrafficByAsinRepository;
import analytic.report.amazon.repository.SalesAndTrafficByDateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataReloadScheduler {
    private static final String DATA_FILE_PATH = "data/test_report.json";
    private final ReportSpecificationRepository reportSpecificationRepository;
    private final SalesAndTrafficByDateRepository salesAndTrafficByDateRepository;
    private final SalesAndTrafficByAsinRepository salesAndTrafficByAsinRepository;
    private final ReportSpecificationMapper reportSpecificationMapper;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 3600000)
    public void checkAndReloadData() {
        try (InputStream is =
                     getClass().getClassLoader().getResourceAsStream(DATA_FILE_PATH)) {

            if (is == null) {
                throw new FileNotFoundException("classpath:" + DATA_FILE_PATH);
            }

            ReportData newReportData = objectMapper.readValue(is, ReportData.class);

            ReportSpecificationResponseDto currentSpec = reportSpecificationMapper
                    .toResponseDto(reportSpecificationRepository.findFirstByOrderByIdAsc());
            ReportSpecificationResponseDto newSpec = reportSpecificationMapper
                    .toResponseDto(newReportData.getReportSpecification());

            if (hasFileChanged(currentSpec, newSpec)) {
                log.info("Data file changed → reloading DB…");
                salesAndTrafficByDateRepository.deleteAll();
                salesAndTrafficByAsinRepository.deleteAll();
                reportSpecificationRepository.deleteAll();

                reportSpecificationRepository.save(newReportData.getReportSpecification());
                salesAndTrafficByDateRepository.saveAll(newReportData.getSalesAndTrafficByDate());
                salesAndTrafficByAsinRepository.saveAll(newReportData.getSalesAndTrafficByAsin());
                log.info("Database updated successfully.");
            } else {
                log.info("No changes detected in the data file.");
            }
        } catch (Exception e) {
            log.error("Failed to reload data: {}", e.getMessage(), e);
        }
    }

    private boolean hasFileChanged(ReportSpecificationResponseDto currentSpec,
                                   ReportSpecificationResponseDto newSpec) {
        if (currentSpec == null) {
            return true;
        }
        return !currentSpec.getDataStartTime().equals(newSpec.getDataStartTime())
                || !currentSpec.getDataEndTime().equals(newSpec.getDataEndTime());
    }
}
