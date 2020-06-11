package fr.tcd.server.amqp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Runner Analysis not sent")
public final class AmqpAnalysisNotSentException extends RuntimeException {
    public AmqpAnalysisNotSentException() {
        super();
    }

    public AmqpAnalysisNotSentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AmqpAnalysisNotSentException(final String message) {
        super(message);
    }

    public AmqpAnalysisNotSentException(final Throwable cause) {
        super(cause);
    }
}
