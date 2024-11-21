package analytic.report.amazon.dataloader;

import analytic.report.amazon.model.ReportData;
import analytic.report.amazon.repository.ReportSpecificationRepository;
import analytic.report.amazon.repository.SalesAndTrafficByAsinRepository;
import analytic.report.amazon.repository.SalesAndTrafficByDateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DatabaseInitializer {
    private static final String DATA_FILE_PATH = "src/main/resources/data/test_report.json";
    private final ReportSpecificationRepository reportSpecificationRepository;
    private final SalesAndTrafficByDateRepository salesAndTrafficByDateRepository;
    private final SalesAndTrafficByAsinRepository salesAndTrafficByAsinRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() throws Exception {
        salesAndTrafficByDateRepository.deleteAll();
        salesAndTrafficByAsinRepository.deleteAll();
        reportSpecificationRepository.deleteAll();
        try {
            File jsonFile = new File(DATA_FILE_PATH);
            log.info("Initializing database from file: {}", DATA_FILE_PATH);
            ReportData reportData = objectMapper.readValue(jsonFile, ReportData.class);
            if (reportData.getReportSpecification() != null) {
                reportSpecificationRepository.save(reportData.getReportSpecification());
                log.info("Saved reportSpecification");
            }
            if (reportData.getSalesAndTrafficByDate() != null) {
                salesAndTrafficByDateRepository.saveAll(reportData.getSalesAndTrafficByDate());
                log.info("Saved salesAndTrafficByDate records: {}",
                        reportData.getSalesAndTrafficByDate().size());
            }
            if (reportData.getSalesAndTrafficByAsin() != null) {
                salesAndTrafficByAsinRepository.saveAll(reportData.getSalesAndTrafficByAsin());
                log.info("Saved salesAndTrafficByAsin records: {}",
                        reportData.getSalesAndTrafficByAsin().size());
            }
        } catch (Exception e) {
            log.error("Failed to initialize database: {}", e.getMessage(), e);
        }
    }
}
