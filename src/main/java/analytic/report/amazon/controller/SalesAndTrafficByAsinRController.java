package analytic.report.amazon.controller;

import analytic.report.amazon.dto.statistics.SalesAndTrafficByAsinResponseDto;
import analytic.report.amazon.service.SalesAndTrafficByAsinService;
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
@RequestMapping(value = "/salesandtrafficbyasin")
public class SalesAndTrafficByAsinRController {
    private final SalesAndTrafficByAsinService service;

    @Operation(
            summary = "Get sales and traffic by asin",
            description = "Returns a list of sales and traffic data filtered by ASINs."
    )
    @GetMapping("/asin")
    @Cacheable(value = "salesAndTrafficByAsin", key = "#asins")
    public List<SalesAndTrafficByAsinResponseDto> getByAsins(@RequestParam List<String> asins) {
        return service.getByAsins(asins);
    }

    @Operation(
            summary = "Get sales and traffic by all asins",
            description = "Returns a list of sales and traffic data for all ASINs."
    )
    @GetMapping("/all")
    @Cacheable(value = "salesAndTrafficByAsin", key = "'all'")
    public List<SalesAndTrafficByAsinResponseDto> getAll() {
        return service.getAll();
    }
}
