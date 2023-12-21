package com.frb.application.wishlist.retriveAllProducts;

import com.frb.domain.product.Product;
import com.frb.domain.wishlist.Wishlist;

import java.util.List;

public record RetriveProductsOutput(
        String id,
        List<ProductOutput> products
) {
    public static RetriveProductsOutput from(final String id, final List<ProductOutput> products) {
        return new RetriveProductsOutput(id, products);
    }

    public static RetriveProductsOutput from(final Wishlist aWishlist) {
        var products = aWishlist.getProducts().stream().map(ProductOutput::from).toList();

        return new RetriveProductsOutput(aWishlist.getId().getValue(), products);
    }

    public record ProductOutput(String id){
        public static ProductOutput from(final Product product) {
            return new ProductOutput(product.getId().getValue());
        }
    }
}
