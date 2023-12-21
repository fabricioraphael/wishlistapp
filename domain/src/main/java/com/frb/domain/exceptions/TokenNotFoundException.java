package com.frb.domain.exceptions;

public class TokenNotFoundException extends NoStacktraceException {
    private static final String defaultMessage = "Authorization token not found";

    protected TokenNotFoundException(final String aMessage) {
        super(aMessage);
    }

    public static TokenNotFoundException with(String message) {
        return new TokenNotFoundException(message);
    }

    public static TokenNotFoundException create() {
        return new TokenNotFoundException(defaultMessage);
    }
}
