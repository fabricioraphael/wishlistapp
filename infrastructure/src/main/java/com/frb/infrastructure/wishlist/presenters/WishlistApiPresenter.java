package com.frb.infrastructure.wishlist.presenters;

import com.frb.application.wishlist.retriveAllProducts.RetriveProductsOutput;
import com.frb.application.wishlist.retriveProduct.RetriveProductOutput;
import com.frb.infrastructure.wishlist.models.ProductResponse;
import com.frb.infrastructure.wishlist.models.WishlistResponse;

public interface WishlistApiPresenter {
    static WishlistResponse present(final RetriveProductsOutput output) {
        var products = output.products().stream().map(ProductResponse::from).toList();
        return new WishlistResponse(
                output.id(),
                products
        );
    }

    static ProductResponse present(final RetriveProductOutput output) {
        return new ProductResponse(
                output.productId()
        );
    }
}