package fr.tcd.server.utils.http_entity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Could not get entity")
public final class GetEntityException extends RuntimeException {
    public GetEntityException() {
        super();
    }

    public GetEntityException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public GetEntityException(final String message) {
        super(message);
    }

    public GetEntityException(final Throwable cause) {
        super(cause);
    }
}
