package com.frb.infrastructure.wishlist.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record WishlistResponse(
        @JsonProperty("wishlistId") String wishlistId,
        @JsonProperty("products") List<ProductResponse> products

) {
}
