package fr.tcd.server.analysis_type;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Analysis Type Not Created")
public final class AnalysisTypeNotCreatedException extends RuntimeException {
    public AnalysisTypeNotCreatedException() {
        super();
    }

    public AnalysisTypeNotCreatedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AnalysisTypeNotCreatedException(final String message) {
        super(message);
    }

    public AnalysisTypeNotCreatedException(final Throwable cause) {
        super(cause);
    }
}
