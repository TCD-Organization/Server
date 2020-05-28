package fr.tcd.server.runner.exception;

public final class RunnerNotCreatedException extends RuntimeException {
    public RunnerNotCreatedException() {
        super();
    }

    public RunnerNotCreatedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RunnerNotCreatedException(final String message) {
        super(message);
    }

    public RunnerNotCreatedException(final Throwable cause) {
        super(cause);
    }
}
