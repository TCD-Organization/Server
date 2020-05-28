package fr.tcd.server.user.exception;

public final class UserNotCreatedException extends RuntimeException {
    public UserNotCreatedException() {
        super();
    }

    public UserNotCreatedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserNotCreatedException(final String message) {
        super(message);
    }

    public UserNotCreatedException(final Throwable cause) {
        super(cause);
    }
}
