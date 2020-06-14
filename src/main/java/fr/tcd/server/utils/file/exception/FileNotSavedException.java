package fr.tcd.server.utils.file.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "File Could not be Saved")
public final class FileNotSavedException extends RuntimeException {
    public FileNotSavedException() {
        super();
    }

    public FileNotSavedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public FileNotSavedException(final String message) {
        super(message);
    }

    public FileNotSavedException(final Throwable cause) {
        super(cause);
    }
}
