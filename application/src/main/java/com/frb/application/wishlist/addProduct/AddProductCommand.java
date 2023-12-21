package com.frb.application.wishlist.addProduct;


public record AddProductCommand(
        String customerId,
        String productId
) {
    public static AddProductCommand with(
            final String customerId,
            final String productId
    ) {
        return new AddProductCommand(customerId, productId);
    }
}
