package analytic.report.amazon.dto.user;

import analytic.report.amazon.validation.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@FieldMatch(first = "password", second = "repeatPassword")
public class UserRegistrationRequestDto {
    @NotBlank
    @Email(message = "Please provide a valid email address")
    private String email;
    @NotBlank
    @Size(min = 4, max = 10, message = "Password must be at least 4 characters long")
    private String password;
    @NotBlank
    @Size(min = 4, max = 10, message = "Password must be at least 4 characters long")
    private String repeatPassword;
    @NotBlank
    private String userName;
}
