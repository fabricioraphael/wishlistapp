package com.frb.application.wishlist.deleteProduct;


public record DeleteProductCommand(
        String customerId,
        String productId
) {
    public static DeleteProductCommand with(
            final String customerId,
            final String productId
    ) {
        return new DeleteProductCommand(customerId, productId);
    }
}
