package com.frb.application.wishlist.retriveProduct;


public record RetriveProductCommand(
        String customerId,
        String productId
) {
    public static RetriveProductCommand with(
            final String customerId,
            final String productId
    ) {
        return new RetriveProductCommand(customerId, productId);
    }
}
