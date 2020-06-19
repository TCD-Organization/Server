package fr.tcd.server.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "User Not Created")
public final class UserNotCreatedException extends RuntimeException {
    public UserNotCreatedException() {
        super();
    }

    public UserNotCreatedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserNotCreatedException(final String message) {
        super(message);
    }

    public UserNotCreatedException(final Throwable cause) {
        super(cause);
    }
}
