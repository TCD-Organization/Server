package fr.tcd.server.document.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Document Not Found")
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
