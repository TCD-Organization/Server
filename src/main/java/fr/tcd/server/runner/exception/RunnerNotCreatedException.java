package fr.tcd.server.runner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Runner Not Created")
public final class RunnerNotCreatedException extends RuntimeException {
    public RunnerNotCreatedException() {
        super();
    }

    public RunnerNotCreatedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RunnerNotCreatedException(final String message) {
        super(message);
    }

    public RunnerNotCreatedException(final Throwable cause) {
        super(cause);
    }
}
