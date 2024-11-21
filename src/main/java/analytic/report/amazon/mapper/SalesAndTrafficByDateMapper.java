package analytic.report.amazon.mapper;

import analytic.report.amazon.config.MapperConfig;
import analytic.report.amazon.dto.statistics.SalesAndTrafficByDateResponseDto;
import analytic.report.amazon.model.SalesAndTrafficByDate;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface SalesAndTrafficByDateMapper {
    SalesAndTrafficByDateResponseDto toResponseDto(SalesAndTrafficByDate entity);
}
