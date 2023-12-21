package com.frb.domain.exceptions;

import com.frb.domain.validation.Error;

import java.util.List;

public class DomainException extends ExceptionWithErrors {

    protected DomainException(final String aMessage, final List<Error> anErrors) {
        super(aMessage, anErrors);
    }

    public static DomainException with(final Error anErrors) {
        return new DomainException(anErrors.message(), List.of(anErrors));
    }

    public static DomainException with(final List<Error> anErrors) {
        return new DomainException("", anErrors);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
