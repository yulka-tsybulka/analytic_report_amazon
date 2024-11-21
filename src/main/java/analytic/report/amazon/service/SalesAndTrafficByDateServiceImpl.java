package analytic.report.amazon.service;

import analytic.report.amazon.dto.statistics.SalesAndTrafficByDateResponseDto;
import analytic.report.amazon.mapper.SalesAndTrafficByDateMapper;
import analytic.report.amazon.repository.SalesAndTrafficByDateRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesAndTrafficByDateServiceImpl implements SalesAndTrafficByDateService {
    private final SalesAndTrafficByDateRepository repository;
    private final SalesAndTrafficByDateMapper mapper;

    @Override
    public List<SalesAndTrafficByDateResponseDto> getByStartDateEndDate(
            String startDate, String endDate) {
        return repository.findByDateRange(startDate, endDate).stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalesAndTrafficByDateResponseDto> getSalesAndTrafficByDate(String date) {
        return repository.findByDate(date).stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalesAndTrafficByDateResponseDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
