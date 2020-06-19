package fr.tcd.server.analysis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Analysis Already Exists")
public final class AnalysisAlreadyExistsException extends RuntimeException {
    public AnalysisAlreadyExistsException() {
        super();
    }

    public AnalysisAlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AnalysisAlreadyExistsException(final String message) {
        super(message);
    }

    public AnalysisAlreadyExistsException(final Throwable cause) {
        super(cause);
    }
}
