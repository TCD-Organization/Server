package fr.tcd.server.utils.file_content.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "File Not Specified")
public final class FileNotSpecifiedException extends RuntimeException {
    public FileNotSpecifiedException() {
        super();
    }

    public FileNotSpecifiedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public FileNotSpecifiedException(final String message) {
        super(message);
    }

    public FileNotSpecifiedException(final Throwable cause) {
        super(cause);
    }
}
