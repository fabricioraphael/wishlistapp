package com.frb.application.wishlist.deleteProduct;

import com.frb.application.wishlist.retriveProduct.RetriveProductCommand;
import com.frb.application.wishlist.retriveProduct.RetriveProductOutput;
import com.frb.application.wishlist.retriveProduct.RetriveProductUseCase;
import com.frb.domain.customer.CustomerID;
import com.frb.domain.exceptions.NotFoundException;
import com.frb.domain.product.ProductID;
import com.frb.domain.validation.Error;
import com.frb.domain.wishlist.WishlistGateway;

import java.util.Objects;
import java.util.function.Supplier;

public class DeleteProductUseCaseImpl extends DeleteProductUseCase {

    private final WishlistGateway wishlistGateway;

    public DeleteProductUseCaseImpl(final WishlistGateway wishlistGateway) {
        this.wishlistGateway = Objects.requireNonNull(wishlistGateway);
    }

    @Override
    public void execute(final DeleteProductCommand aCommand) {
        final var aCustomerId = CustomerID.from(aCommand.customerId());
        final var aProductId = ProductID.from(aCommand.productId());

        this.wishlistGateway.deleteProductByCustomerId(aCustomerId, aProductId);

//        var wishlistResult = this.wishlistGateway.findByCustomerId(aCustomerId);
//
//        if (wishlistResult.isPresent()) {
//            var actualWishlist = wishlistResult.get();
//
//            actualWishlist.removeProductById(aProductId);
//
//            this.wishlistGateway.save(actualWishlist);
//        }
    }

    private Supplier<NotFoundException> productNotFound() {
        return () -> NotFoundException.with(new Error("Product not found on wishlist"));
    }
}