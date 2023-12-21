package com.frb.infrastructure.configuration;

import com.frb.domain.exceptions.CredentialDataExtractingErrorHandler;
import com.frb.infrastructure.api.filters.CredentialDataExtractor;
import com.frb.infrastructure.api.filters.JWTDecoder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegister {

    private final JWTDecoder jwtDecoder;

    private final CredentialDataExtractingErrorHandler errorHandler;

    public FilterRegister(final JWTDecoder jwtDecoder, final CredentialDataExtractingErrorHandler errorHandler) {
        this.jwtDecoder = jwtDecoder;
        this.errorHandler = errorHandler;
    }

    @Bean
    public FilterRegistrationBean registryCredentialDataExtractorFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CredentialDataExtractor(jwtDecoder, errorHandler));
        registration.addUrlPatterns("/wishlists/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("someFilter");
        registration.setOrder(1);
        return registration;
    }
}
