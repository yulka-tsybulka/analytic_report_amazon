package analytic.report.amazon.model;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "reportSpecification")
public class ReportSpecification implements Serializable {
    @Id
    private String id;
    private String reportType;
    private ReportOptions reportOptions;
    private String dataStartTime;
    private String dataEndTime;
    private List<String> marketplaceIds;

    @Data
    public static class ReportOptions {
        private String dateGranularity;
        private String asinGranularity;
    }
}
