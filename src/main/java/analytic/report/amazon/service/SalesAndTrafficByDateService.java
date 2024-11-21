package analytic.report.amazon.service;

import analytic.report.amazon.dto.statistics.SalesAndTrafficByDateResponseDto;
import java.util.List;

public interface SalesAndTrafficByDateService {
    List<SalesAndTrafficByDateResponseDto> getByStartDateEndDate(String startDate, String endDate);

    List<SalesAndTrafficByDateResponseDto> getSalesAndTrafficByDate(String date);

    List<SalesAndTrafficByDateResponseDto> getAll();
}
