package fr.tcd.server.analysis.exception;

public final class RunnerAnalysisNotSentException extends RuntimeException {
    public RunnerAnalysisNotSentException() {
        super();
    }

    public RunnerAnalysisNotSentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RunnerAnalysisNotSentException(final String message) {
        super(message);
    }

    public RunnerAnalysisNotSentException(final Throwable cause) {
        super(cause);
    }
}
