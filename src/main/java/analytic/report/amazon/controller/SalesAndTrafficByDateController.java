package analytic.report.amazon.controller;

import analytic.report.amazon.dto.statistics.SalesAndTrafficByDateResponseDto;
import analytic.report.amazon.service.SalesAndTrafficByDateService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/salesandtrafficbydate")
public class SalesAndTrafficByDateController {
    private final SalesAndTrafficByDateService service;

    @Operation(summary = "Get sales and traffic by date range",
            description = "Returns a list of sales and traffic data "
                   + "filtered by the start and end dates."
    )
    @GetMapping("/range")
    @Cacheable(value = "salesAndTrafficByDate", key = "#startDate + '-' + #endDate")
    public List<SalesAndTrafficByDateResponseDto> getByStartDateEndDate(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return service.getByStartDateEndDate(startDate, endDate);
    }

    @Operation(summary = "Get sales and traffic by specific date",
            description = "Returns a list of sales and traffic data "
                    + "for the specified date."
    )
    @GetMapping("/date")
    @Cacheable(value = "salesAndTrafficByDate", key = "#date")
    public List<SalesAndTrafficByDateResponseDto> getSalesAndTrafficByDate(
            @RequestParam String date) {
        return service.getSalesAndTrafficByDate(date);
    }

    @Operation(summary = "Get all sales and traffic data",
            description = "Returns a list of all sales and traffic data."
    )
    @GetMapping("/all")
    @Cacheable(value = "salesAndTrafficByDate", key = "'all'")
    public List<SalesAndTrafficByDateResponseDto> getAll() {
        return service.getAll();
    }
}
