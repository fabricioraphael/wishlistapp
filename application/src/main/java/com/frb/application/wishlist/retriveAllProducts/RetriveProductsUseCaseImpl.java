package com.frb.application.wishlist.retriveAllProducts;

import com.frb.domain.customer.CustomerID;
import com.frb.domain.exceptions.NotFoundException;
import com.frb.domain.validation.Error;
import com.frb.domain.wishlist.WishlistGateway;
import java.util.Objects;
import java.util.function.Supplier;

public class RetriveProductsUseCaseImpl extends RetriveProductsUseCase {

    private final WishlistGateway wishlistGateway;

    public RetriveProductsUseCaseImpl(final WishlistGateway wishlistGateway) {
        this.wishlistGateway = Objects.requireNonNull(wishlistGateway);
    }

    @Override
    public RetriveProductsOutput execute(final RetriveProductsCommand aCommand) {
        final var aCustomerId = CustomerID.from(aCommand.customerId());

        var wishlistResult = this.wishlistGateway.findByCustomerId(aCustomerId);

        return wishlistResult.map(RetriveProductsOutput::from).orElseThrow(notFound());
    }

    private Supplier<NotFoundException> notFound() {
        return () -> NotFoundException.with(new Error("Wishlist was not found"));
    }
}