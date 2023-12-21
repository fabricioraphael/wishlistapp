package com.frb.domain.token;

import java.util.Optional;

public interface TokenDecoder {

    public Optional<CredentialData> decode(String token);
}
