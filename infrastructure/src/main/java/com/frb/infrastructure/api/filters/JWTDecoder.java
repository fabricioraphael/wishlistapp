package com.frb.infrastructure.api.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frb.domain.token.CredentialData;
import com.frb.domain.token.TokenDecoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JWTDecoder implements TokenDecoder {
    private final ObjectMapper mapper;

    public JWTDecoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Optional<CredentialData> decode(String token) {
        java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();
        String[] split_string = token.split("\\.");
        String base64EncodedBody = split_string[1];

        String body = new String(decoder.decode(base64EncodedBody));

        try {
            return Optional.of(mapper.readValue(body, CredentialData.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
