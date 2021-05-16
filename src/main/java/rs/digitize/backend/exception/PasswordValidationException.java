package rs.digitize.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Your password must be at least 8 characters long and contain at least 1 number and 1 uppercase letter")
public class PasswordValidationException extends RuntimeException {
}
