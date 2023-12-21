package com.frb.application.wishlist.retriveProduct;

import com.frb.domain.customer.CustomerID;
import com.frb.domain.exceptions.NotFoundException;
import com.frb.domain.product.ProductID;
import com.frb.domain.validation.Error;
import com.frb.domain.wishlist.WishlistGateway;

import java.util.Objects;
import java.util.function.Supplier;

public class RetriveProductUseCaseImpl extends RetriveProductUseCase {

    private final WishlistGateway wishlistGateway;

    public RetriveProductUseCaseImpl(final WishlistGateway wishlistGateway) {
        this.wishlistGateway = Objects.requireNonNull(wishlistGateway);
    }

    @Override
    public RetriveProductOutput execute(final RetriveProductCommand aCommand) {
        final var aCustomerId = CustomerID.from(aCommand.customerId());
        final var aProductId = ProductID.from(aCommand.productId());

        var wishlistResult = this.wishlistGateway.findByCustomerId(aCustomerId);

        if (wishlistResult.isPresent()) {
            return wishlistResult.get().retriveProduct(aProductId)
                    .map(RetriveProductOutput::from).orElseThrow(productNotFound());
        }

        throw productNotFound().get();
    }

    private Supplier<NotFoundException> productNotFound() {
        return () -> NotFoundException.with(new Error("Product not found on wishlist"));
    }
}