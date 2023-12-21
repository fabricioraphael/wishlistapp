package com.frb.domain.token;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CredentialData(
        @JsonProperty("customerId")
        String customerId
) {
}
