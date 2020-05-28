package fr.tcd.server.analysis.exception;

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
