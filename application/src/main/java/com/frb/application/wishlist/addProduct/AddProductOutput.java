package com.frb.application.wishlist.addProduct;

import com.frb.domain.wishlist.Wishlist;

public record AddProductOutput(
        String id
) {
    public static AddProductOutput from(final String id) {
        return new AddProductOutput(id);
    }

    public static AddProductOutput from(final Wishlist aWishlist) {
        return new AddProductOutput(aWishlist.getId().getValue());
    }
}
