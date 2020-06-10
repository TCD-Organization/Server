package fr.tcd.server.analysis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Analysis not sent")
public final class AnalysisNotSentException extends RuntimeException {
    public AnalysisNotSentException() {
        super();
    }

    public AnalysisNotSentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AnalysisNotSentException(final String message) {
        super(message);
    }

    public AnalysisNotSentException(final Throwable cause) {
        super(cause);
    }
}
