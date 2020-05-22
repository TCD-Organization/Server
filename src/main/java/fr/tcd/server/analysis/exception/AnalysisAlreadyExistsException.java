package fr.tcd.server.analysis.exception;

//TODO: Remove later if not used
public final class AnalysisAlreadyExistsException extends RuntimeException {
    //private static final long serialVersionUID = 5861310537366287163L;

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
