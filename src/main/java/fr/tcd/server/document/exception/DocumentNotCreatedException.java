package fr.tcd.server.document.exception;

public final class DocumentNotCreatedException extends RuntimeException {
    public DocumentNotCreatedException() {
        super();
    }

    public DocumentNotCreatedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DocumentNotCreatedException(final String message) {
        super(message);
    }

    public DocumentNotCreatedException(final Throwable cause) {
        super(cause);
    }
}
