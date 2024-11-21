package analytic.report.amazon.model;

import java.util.List;
import lombok.Data;

@Data
public class ReportData {
    private ReportSpecification reportSpecification;
    private List<SalesAndTrafficByDate> salesAndTrafficByDate;
    private List<SalesAndTrafficByAsin> salesAndTrafficByAsin;

}
