package fr.tcd.server.analysis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Analysis Not Created")
public final class AnalysisNotCreatedException extends RuntimeException {
    public AnalysisNotCreatedException() {
        super();
    }

    public AnalysisNotCreatedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AnalysisNotCreatedException(final String message) {
        super(message);
    }

    public AnalysisNotCreatedException(final Throwable cause) {
        super(cause);
    }
}
