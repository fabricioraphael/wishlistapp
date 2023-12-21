package com.frb.domain.product;

import com.frb.domain.AggregateRoot;
import com.frb.domain.customer.CustomerID;
import com.frb.domain.validation.ValidationHandler;
import com.frb.domain.wishlist.WishlistID;
import com.frb.domain.wishlist.WishlistValidator;

public class Product extends AggregateRoot<ProductID> implements Cloneable {

    private Product(
            final ProductID anId
    ) {
        super(anId);
    }

    public static Product with(
            final ProductID anId
    ) {
        return new Product(
                anId
        );
    }

    public static Product with(final Product aProduct) {
        return with(
                aProduct.getId()
        );
    }

    @Override
    public ProductID getId() {
        return super.getId();
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new ProductValidator(this, handler).validate();
    }

    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + getId().getValue() + '\'' +
                '}';
    }
}
