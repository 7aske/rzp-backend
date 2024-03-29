package rs.digitize.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "password.invalid")
public class InvalidPasswordException extends RuntimeException {
}
