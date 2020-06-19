package fr.tcd.server.analysis_type.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Analysis Type Already Exists")
public final class AnalysisTypeAlreadyExistsException extends RuntimeException {
    public AnalysisTypeAlreadyExistsException() {
        super();
    }

    public AnalysisTypeAlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AnalysisTypeAlreadyExistsException(final String message) {
        super(message);
    }

    public AnalysisTypeAlreadyExistsException(final Throwable cause) {
        super(cause);
    }
}
