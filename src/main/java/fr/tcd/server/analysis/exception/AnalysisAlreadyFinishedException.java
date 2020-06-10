package fr.tcd.server.analysis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_MODIFIED, reason = "Analysis Already Finished and Not Modified")
public final class AnalysisAlreadyFinishedException extends RuntimeException {
    public AnalysisAlreadyFinishedException() {
        super();
    }

    public AnalysisAlreadyFinishedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AnalysisAlreadyFinishedException(final String message) {
        super(message);
    }

    public AnalysisAlreadyFinishedException(final Throwable cause) {
        super(cause);
    }
}
