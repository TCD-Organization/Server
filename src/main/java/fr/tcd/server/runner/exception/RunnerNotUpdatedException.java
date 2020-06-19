package fr.tcd.server.runner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Runner Not Updated")
public final class RunnerNotUpdatedException extends RuntimeException {
    public RunnerNotUpdatedException() {
        super();
    }

    public RunnerNotUpdatedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RunnerNotUpdatedException(final String message) {
        super(message);
    }

    public RunnerNotUpdatedException(final Throwable cause) {
        super(cause);
    }
}
