package fr.tcd.server.analysis_type.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Analysis Type Does Not Exist")
public final class AnalysisTypeDoesNotExistException extends RuntimeException {
    public AnalysisTypeDoesNotExistException() {
        super();
    }

    public AnalysisTypeDoesNotExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AnalysisTypeDoesNotExistException(final String message) {
        super(message);
    }

    public AnalysisTypeDoesNotExistException(final Throwable cause) {
        super(cause);
    }
}
