package com.frb.application.wishlist.retriveAllProducts;


public record RetriveProductsCommand(
        String customerId
) {
    public static RetriveProductsCommand with(
            final String customerId
    ) {
        return new RetriveProductsCommand(customerId);
    }
}
