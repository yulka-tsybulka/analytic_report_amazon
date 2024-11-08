package analytic.report.amazon.service;

import analytic.report.amazon.dto.user.UserRegistrationRequestDto;
import analytic.report.amazon.dto.user.UserResponseDto;
import analytic.report.amazon.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException;
}
