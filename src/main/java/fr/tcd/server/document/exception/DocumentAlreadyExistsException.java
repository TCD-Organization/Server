package fr.tcd.server.document.exception;

public final class DocumentAlreadyExistsException extends RuntimeException {
    //private static final long serialVersionUID = 5861310537366287163L;

    public DocumentAlreadyExistsException() {
        super();
    }

    public DocumentAlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DocumentAlreadyExistsException(final String message) {
        super(message);
    }

    public DocumentAlreadyExistsException(final Throwable cause) {
        super(cause);
    }
}
