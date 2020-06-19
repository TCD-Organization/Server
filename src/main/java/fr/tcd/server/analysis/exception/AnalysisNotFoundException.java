package fr.tcd.server.analysis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Analysis Not Found")
public final class AnalysisNotFoundException extends RuntimeException {
    public AnalysisNotFoundException() {
        super();
    }

    public AnalysisNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AnalysisNotFoundException(final String message) {
        super(message);
    }

    public AnalysisNotFoundException(final Throwable cause) {
        super(cause);
    }
}
