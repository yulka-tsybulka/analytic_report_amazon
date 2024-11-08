package analytic.report.amazon.mapper;

import analytic.report.amazon.config.MapperConfig;
import analytic.report.amazon.dto.user.UserRegistrationRequestDto;
import analytic.report.amazon.dto.user.UserResponseDto;
import analytic.report.amazon.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto requestDto);
}
