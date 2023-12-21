package com.frb.infrastructure.api.filters;

import com.frb.domain.exceptions.CredentialDataExtractingErrorHandler;
import com.frb.domain.exceptions.TokenNotFoundException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class CredentialDataExtractor implements Filter {

    private final JWTDecoder jwtDecoder;
    private final CredentialDataExtractingErrorHandler errorHandler;


    public CredentialDataExtractor(JWTDecoder jwtDecoder, final CredentialDataExtractingErrorHandler errorHandler) {
        this.jwtDecoder = jwtDecoder;
        this.errorHandler = errorHandler;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var token = extractTokenFromRequest((HttpServletRequest) servletRequest);

        if (token.isPresent()) {
            var credentialsData = jwtDecoder.decode(token.get());

            servletRequest.setAttribute("credentialData", credentialsData.get());
        } else {
            errorHandler.handler(servletResponse, TokenNotFoundException.create());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    public Optional<String> extractTokenFromRequest(HttpServletRequest request) {
        try {
            var token = request.getHeader("Authorization")
                    .trim();
            if (!token.isBlank())
                return Optional.of(token);
            else
                return Optional.empty();
        } catch (NullPointerException ex) {
            return Optional.empty();
        }
    }
}
