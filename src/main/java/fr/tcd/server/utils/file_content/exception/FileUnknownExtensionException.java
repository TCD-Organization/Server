package fr.tcd.server.utils.file_content.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Unkwnown File Extension, please use PDF or TXT")
public final class FileUnknownExtensionException extends RuntimeException {
    public FileUnknownExtensionException() {
        super();
    }

    public FileUnknownExtensionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public FileUnknownExtensionException(final String message) {
        super(message);
    }

    public FileUnknownExtensionException(final Throwable cause) {
        super(cause);
    }
}
