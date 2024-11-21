package analytic.report.amazon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AnalyticReportAmazonApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyticReportAmazonApplication.class, args);
    }
}
