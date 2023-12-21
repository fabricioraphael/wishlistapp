package com.frb.infrastructure.wishlist.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddProductRequest(
        @JsonProperty("productId") String productId
) { }
