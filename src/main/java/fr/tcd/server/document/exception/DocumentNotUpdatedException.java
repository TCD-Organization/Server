package fr.tcd.server.document.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Document Not Updated")
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
