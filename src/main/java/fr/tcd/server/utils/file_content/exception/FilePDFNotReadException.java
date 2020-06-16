package fr.tcd.server.utils.file_content.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Document PDF Could not be Read")
public final class FilePDFNotReadException extends RuntimeException {
    public FilePDFNotReadException() {
        super();
    }

    public FilePDFNotReadException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public FilePDFNotReadException(final String message) {
        super(message);
    }

    public FilePDFNotReadException(final Throwable cause) {
        super(cause);
    }
}
