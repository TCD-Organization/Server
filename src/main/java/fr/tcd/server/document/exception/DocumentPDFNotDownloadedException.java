package fr.tcd.server.document.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Document PDF Could not be Downloaded")
public final class DocumentPDFNotDownloadedException extends RuntimeException {
    public DocumentPDFNotDownloadedException() {
        super();
    }

    public DocumentPDFNotDownloadedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DocumentPDFNotDownloadedException(final String message) {
        super(message);
    }

    public DocumentPDFNotDownloadedException(final Throwable cause) {
        super(cause);
    }
}
