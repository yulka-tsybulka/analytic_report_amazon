package analytic.report.amazon.mapper;

import analytic.report.amazon.config.MapperConfig;
import analytic.report.amazon.dto.statistics.ReportSpecificationResponseDto;
import analytic.report.amazon.model.ReportSpecification;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ReportSpecificationMapper {
    ReportSpecificationResponseDto toResponseDto(ReportSpecification entity);
}
