package com.frb.infrastructure.service;

import com.frb.domain.exceptions.CredentialDataExtractingErrorHandler;
import jakarta.servlet.ServletResponse;
import org.springframework.stereotype.Service;

@Service
public class CredentialDataExtractingErrorHandlerImpl implements CredentialDataExtractingErrorHandler {

    @Override
    public void handler(ServletResponse response, Throwable exception) {

        System.out.println(">>>>>>>>>>>>>>>>>>>> CredentialDataExtractingErrorHandlerImpl: " + exception.getMessage());
    }
}
