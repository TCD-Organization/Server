package fr.tcd.server.runner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Runner Already Exists")
public final class RunnerAlreadyExistsException extends RuntimeException {
    public RunnerAlreadyExistsException() {
        super();
    }

    public RunnerAlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RunnerAlreadyExistsException(final String message) {
        super(message);
    }

    public RunnerAlreadyExistsException(final Throwable cause) {
        super(cause);
    }
}
