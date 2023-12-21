package com.frb.domain.product;

import com.frb.domain.validation.ValidationHandler;
import com.frb.domain.validation.Validator;
import com.frb.domain.wishlist.Wishlist;

public class ProductValidator extends Validator {
    private final Product product;

    public ProductValidator(final Product aProduct, final ValidationHandler aHandler) {
        super(aHandler);
        this.product = aProduct;
    }

    @Override
    public void validate() {
    }
}