package analytic.report.amazon.service;

import analytic.report.amazon.dto.statistics.SalesAndTrafficByAsinResponseDto;
import java.util.List;

public interface SalesAndTrafficByAsinService {
    List<SalesAndTrafficByAsinResponseDto> getByAsins(List<String> asins);

    List<SalesAndTrafficByAsinResponseDto> getAll();
}
