package analytic.report.amazon.dto.statistics;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ReportSpecificationResponseDto implements Serializable {
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
