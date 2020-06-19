package fr.tcd.server.document.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Document Content Not Retrieved")
public final class DocumentContentNotRetrievedException extends RuntimeException {
    public DocumentContentNotRetrievedException() {
        super();
    }

    public DocumentContentNotRetrievedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DocumentContentNotRetrievedException(final String message) {
        super(message);
    }

    public DocumentContentNotRetrievedException(final Throwable cause) {
        super(cause);
    }
}
