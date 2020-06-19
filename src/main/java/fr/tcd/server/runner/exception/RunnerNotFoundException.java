package fr.tcd.server.runner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Runner Not Found")
public final class RunnerNotFoundException extends RuntimeException {
    public RunnerNotFoundException() {
        super();
    }

    public RunnerNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RunnerNotFoundException(final String message) {
        super(message);
    }

    public RunnerNotFoundException(final Throwable cause) {
        super(cause);
    }
}
