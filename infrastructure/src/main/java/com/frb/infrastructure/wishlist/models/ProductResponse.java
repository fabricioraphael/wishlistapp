package com.frb.infrastructure.wishlist.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.frb.application.wishlist.retriveAllProducts.RetriveProductsOutput;

public record ProductResponse(
        @JsonProperty("productId") String productId

) {

    public static ProductResponse from(RetriveProductsOutput.ProductOutput productsOutput){
        return new ProductResponse(
                productsOutput.id()
        );
    }
}
