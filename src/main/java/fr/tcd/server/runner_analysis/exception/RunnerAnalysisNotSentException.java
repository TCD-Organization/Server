package fr.tcd.server.runner_analysis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Runner Analysis not sent")
public final class RunnerAnalysisNotSentException extends RuntimeException {
    public RunnerAnalysisNotSentException() {
        super();
    }

    public RunnerAnalysisNotSentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RunnerAnalysisNotSentException(final String message) {
        super(message);
    }

    public RunnerAnalysisNotSentException(final Throwable cause) {
        super(cause);
    }
}
