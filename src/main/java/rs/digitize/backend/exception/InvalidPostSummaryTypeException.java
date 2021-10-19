package rs.digitize.backend.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(code = BAD_REQUEST, reason = "post.summary.invalid-type")
public class InvalidPostSummaryTypeException extends RuntimeException {
}
