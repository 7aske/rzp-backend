package rs.digitize.backend.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(code = BAD_REQUEST, reason = "password.validation.change.not-match")
public class ChangePasswordPasswordsDoNotMatchException extends RuntimeException {
}
