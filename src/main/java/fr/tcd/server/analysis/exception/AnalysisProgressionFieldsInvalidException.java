package fr.tcd.server.analysis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public final class AnalysisProgressionFieldsInvalidException extends RuntimeException {
    public AnalysisProgressionFieldsInvalidException() {
        super();
    }

    public AnalysisProgressionFieldsInvalidException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AnalysisProgressionFieldsInvalidException(final String message) {
        super(message);
    }

    public AnalysisProgressionFieldsInvalidException(final Throwable cause) {
        super(cause);
    }
}
