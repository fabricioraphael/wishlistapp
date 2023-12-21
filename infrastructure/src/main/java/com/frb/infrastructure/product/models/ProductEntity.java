package com.frb.infrastructure.product.models;

import com.frb.domain.product.Product;
import com.frb.domain.wishlist.Wishlist;
import com.frb.infrastructure.wishlist.persistence.WishlistEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public class ProductEntity {
    private String id;

    public ProductEntity() {
    }

    private ProductEntity(final String id) {
        this.id = id;
    }

    public static ProductEntity from(final Product aProduct) {
        return new ProductEntity(
                aProduct.getId().getValue()
        );
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id='" + id + '\'' +
                '}';
    }
}
