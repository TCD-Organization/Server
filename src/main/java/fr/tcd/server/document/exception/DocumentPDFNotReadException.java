package fr.tcd.server.document.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Document PDF Could not be Read")
public final class DocumentPDFNotReadException extends RuntimeException {
    public DocumentPDFNotReadException() {
        super();
    }

    public DocumentPDFNotReadException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DocumentPDFNotReadException(final String message) {
        super(message);
    }

    public DocumentPDFNotReadException(final Throwable cause) {
        super(cause);
    }
}
