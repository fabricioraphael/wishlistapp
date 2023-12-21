package com.frb.domain.exceptions;

import jakarta.servlet.ServletResponse;

public interface CredentialDataExtractingErrorHandler {

    void handler(ServletResponse response, Throwable exception);
}
