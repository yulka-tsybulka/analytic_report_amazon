package analytic.report.amazon.service;

import analytic.report.amazon.dto.user.UserRegistrationRequestDto;
import analytic.report.amazon.dto.user.UserResponseDto;
import analytic.report.amazon.exception.RegistrationException;
import analytic.report.amazon.mapper.UserMapper;
import analytic.report.amazon.model.User;
import analytic.report.amazon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("The user with this email is already registered "
                    + requestDto.getEmail());
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }
}
