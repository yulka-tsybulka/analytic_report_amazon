package analytic.report.amazon.service;

import analytic.report.amazon.dto.statistics.SalesAndTrafficByAsinResponseDto;
import analytic.report.amazon.mapper.SalesAndTrafficByAsinMapper;
import analytic.report.amazon.repository.SalesAndTrafficByAsinRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesAndTrafficByAsinServiceImpl implements SalesAndTrafficByAsinService {
    private final SalesAndTrafficByAsinRepository repository;
    private final SalesAndTrafficByAsinMapper mapper;

    @Override
    public List<SalesAndTrafficByAsinResponseDto> getByAsins(List<String> asins) {
        return repository.findByParentAsinIn(asins).stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalesAndTrafficByAsinResponseDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
