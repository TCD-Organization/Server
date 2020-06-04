package fr.tcd.server.document.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Document Not Created")
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
