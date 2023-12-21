package com.frb.infrastructure.shared;


import com.frb.domain.exceptions.TokenNotFoundException;
import com.frb.domain.token.CredentialData;
import com.frb.domain.token.CredentialDataProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class CredentialDataProviderImpl implements CredentialDataProvider {
    private final HttpServletRequest request;

    public CredentialDataProviderImpl(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public CredentialData getCredentialData() {
        var credential = (CredentialData) request.getAttribute("credentialData");

        if (credential == null) {
            throw TokenNotFoundException.create();
        }

        if (credential.customerId() == null) {
            throw TokenNotFoundException.with("Invalid token. 'customerId' not found");
        }

        return credential;
    }
}
