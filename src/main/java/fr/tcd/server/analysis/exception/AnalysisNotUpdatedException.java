package fr.tcd.server.analysis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Analysis Not Updated")
public final class AnalysisNotUpdatedException extends RuntimeException {
    public AnalysisNotUpdatedException() {
        super();
    }

    public AnalysisNotUpdatedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AnalysisNotUpdatedException(final String message) {
        super(message);
    }

    public AnalysisNotUpdatedException(final Throwable cause) {
        super(cause);
    }
}
