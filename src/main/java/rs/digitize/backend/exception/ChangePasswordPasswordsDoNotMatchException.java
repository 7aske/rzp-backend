package rs.digitize.backend.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(code = BAD_REQUEST, reason = "Passwords do not match")
public class ChangePasswordPasswordsDoNotMatchException extends RuntimeException {
}
