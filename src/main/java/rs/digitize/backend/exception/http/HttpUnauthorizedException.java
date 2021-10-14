package rs.digitize.backend.exception.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Access is denied")
public class HttpUnauthorizedException extends RuntimeException {
	public HttpUnauthorizedException() {
	}
}
