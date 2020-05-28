package fr.tcd.server.document.exception;

public final class DocumentNotFoundException extends RuntimeException {
    public DocumentNotFoundException() {
        super();
    }

    public DocumentNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DocumentNotFoundException(final String message) {
        super(message);
    }

    public DocumentNotFoundException(final Throwable cause) {
        super(cause);
    }
}
