package analytic.report.amazon.dataloader;

import analytic.report.amazon.dto.statistics.ReportSpecificationResponseDto;
import analytic.report.amazon.mapper.ReportSpecificationMapper;
import analytic.report.amazon.model.ReportData;
import analytic.report.amazon.repository.ReportSpecificationRepository;
import analytic.report.amazon.repository.SalesAndTrafficByAsinRepository;
import analytic.report.amazon.repository.SalesAndTrafficByDateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataReloadScheduler {
    private static final String DATA_FILE_PATH = "src/main/resources/data/test_report.json";
    private final ReportSpecificationRepository reportSpecificationRepository;
    private final SalesAndTrafficByDateRepository salesAndTrafficByDateRepository;
    private final SalesAndTrafficByAsinRepository salesAndTrafficByAsinRepository;
    private final ReportSpecificationMapper reportSpecificationMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(fixedRate = 3600000)
    public void checkAndReloadData() {
        try {
            File jsonFile = new File(DATA_FILE_PATH);
            log.info("Checking for updates in the file: {}", DATA_FILE_PATH);
            ReportData newReportData = objectMapper.readValue(jsonFile, ReportData.class);
            ReportSpecificationResponseDto currentSpecification = reportSpecificationMapper
                    .toResponseDto(reportSpecificationRepository.findFirstByOrderByIdAsc());
            ReportSpecificationResponseDto newSpecification = reportSpecificationMapper
                    .toResponseDto(newReportData.getReportSpecification());
            if (hasFileChanged(currentSpecification, newSpecification)) {
                log.info("Data file has changed. Updating database...");
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

    private boolean hasFileChanged(ReportSpecificationResponseDto currentSpecification,
                                   ReportSpecificationResponseDto newSpecification) {
        if (currentSpecification == null) {
            log.warn("No existing reportSpecification found. Treating file as new.");
            return true;
        }
        return !currentSpecification.getDataStartTime().equals(newSpecification.getDataStartTime())
                || !currentSpecification.getDataEndTime().equals(newSpecification.getDataEndTime());
    }
}
