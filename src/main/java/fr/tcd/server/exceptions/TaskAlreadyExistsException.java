package fr.tcd.server.exceptions;

public final class TaskAlreadyExistsException extends RuntimeException {
    //private static final long serialVersionUID = 5861310537366287163L;

    public TaskAlreadyExistsException() {
        super();
    }

    public TaskAlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TaskAlreadyExistsException(final String message) {
        super(message);
    }

    public TaskAlreadyExistsException(final Throwable cause) {
        super(cause);
    }
}
