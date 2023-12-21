package com.frb.application.wishlist.retriveProduct;

import com.frb.domain.product.Product;
import com.frb.domain.wishlist.Wishlist;

import java.util.List;

public record RetriveProductOutput(
        String productId
) {
    public static RetriveProductOutput from(final String productId) {
        return new RetriveProductOutput(productId);
    }

    public static RetriveProductOutput from(final Product aProduct) {
        return new RetriveProductOutput(aProduct.getId().getValue());
    }
}
