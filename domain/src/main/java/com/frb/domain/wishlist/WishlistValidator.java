package com.frb.domain.wishlist;

import com.frb.domain.validation.Error;
import com.frb.domain.validation.ValidationHandler;
import com.frb.domain.validation.Validator;

import java.math.BigDecimal;

public class WishlistValidator extends Validator {

    public static final int MAX_PRODUCTS_SIZE = 20;
    private final Wishlist wishlist;

    public WishlistValidator(final Wishlist aWishlist, final ValidationHandler aHandler) {
        super(aHandler);
        this.wishlist = aWishlist;
    }

    @Override
    public void validate() {
        checkProductsSize();
    }

    private void checkProductsSize() {
        final var products = this.wishlist.getProducts();

        if (products.size() > MAX_PRODUCTS_SIZE) {
            this.validationHandler().append(new Error("the quantity of 'products' cannot be greater than %s.".formatted(MAX_PRODUCTS_SIZE)));
        }
    }
}