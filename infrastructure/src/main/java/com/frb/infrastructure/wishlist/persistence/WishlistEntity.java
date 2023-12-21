package com.frb.infrastructure.wishlist.persistence;


import com.frb.domain.customer.CustomerID;
import com.frb.domain.product.Product;
import com.frb.domain.product.ProductID;
import com.frb.domain.wishlist.Wishlist;
import com.frb.domain.wishlist.WishlistID;
import com.frb.infrastructure.product.models.ProductEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "wishlists")
public class WishlistEntity {
    @Id
    private String id;

    @Field(name="customerId")
    private String customerId;

//    @DBRef
    private List<ProductEntity> products;

    public WishlistEntity() {
    }

    private WishlistEntity(
            final String id,
            final String customerId,
            final List<ProductEntity> products
    ) {
        this.id = id;
        this.customerId = customerId;
        this.products = products;
    }

    public static WishlistEntity from(final Wishlist aWishlist) {
        final var products = aWishlist.getProducts().stream().map(ProductEntity::from).toList();

        return new WishlistEntity(
                aWishlist.getId().getValue(),
                aWishlist.getCustomerId().getValue(),
                products
        );
    }

    public Wishlist toAggregate() {
        var products = this.products.isEmpty() ? new ArrayList<Product>() : this.products.stream().map(prod -> Product.with(ProductID.from(prod.getId()))).toList();

        return Wishlist.with(
                WishlistID.from(id),
                CustomerID.from(customerId),
                products
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "WishlistEntity{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", products=" + products +
                '}';
    }
}
