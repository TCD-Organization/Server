package fr.tcd.server.document.exception;

public final class DocumentNotUpdatedException extends RuntimeException {
    public DocumentNotUpdatedException() {
        super();
    }

    public DocumentNotUpdatedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DocumentNotUpdatedException(final String message) {
        super(message);
    }

    public DocumentNotUpdatedException(final Throwable cause) {
        super(cause);
    }
}
