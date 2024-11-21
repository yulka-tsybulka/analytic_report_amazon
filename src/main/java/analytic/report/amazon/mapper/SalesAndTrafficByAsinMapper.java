package analytic.report.amazon.mapper;

import analytic.report.amazon.config.MapperConfig;
import analytic.report.amazon.dto.statistics.SalesAndTrafficByAsinResponseDto;
import analytic.report.amazon.model.SalesAndTrafficByAsin;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface SalesAndTrafficByAsinMapper {
    SalesAndTrafficByAsinResponseDto toResponseDto(SalesAndTrafficByAsin entity);
}
