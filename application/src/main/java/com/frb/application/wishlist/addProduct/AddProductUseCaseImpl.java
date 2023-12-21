package com.frb.application.wishlist.addProduct;

import com.frb.domain.customer.CustomerID;
import com.frb.domain.product.Product;
import com.frb.domain.product.ProductID;
import com.frb.domain.wishlist.Wishlist;
import com.frb.domain.wishlist.WishlistGateway;
import com.frb.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.List;
import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class AddProductUseCaseImpl extends AddProductUseCase {

    private final WishlistGateway wishlistGateway;

    public AddProductUseCaseImpl(final WishlistGateway wishlistGateway) {
        this.wishlistGateway = Objects.requireNonNull(wishlistGateway);
    }

    @Override
    public Either<Notification, AddProductOutput> execute(final AddProductCommand aCommand) {
        final var aCustomerId = CustomerID.from(aCommand.customerId());
        final var aProductId = ProductID.from(aCommand.productId());

        var wishlistOfCustomer = wishlistGateway.findByCustomerId(aCustomerId);

        final var notification = Notification.create();

        if (wishlistOfCustomer.isPresent()) {
            var wishListToSave = wishlistOfCustomer.get();

            wishListToSave.addProduct(Product.with(aProductId), notification);
//            wishListToSave.validate(notification);

            return notification.hasError() ? Left(notification) : create(wishListToSave);
        } else {
            final var products = List.of(Product.with(aProductId));

            final var aWishlist = Wishlist.newWishlist(aCustomerId, products);

            return notification.hasError() ? Left(notification) : create(aWishlist);
        }
    }

    private Either<Notification, AddProductOutput> create(final Wishlist aWishlist) {
        return Try(() -> this.wishlistGateway.addProduct(aWishlist))
                .toEither()
                .bimap(Notification::create, AddProductOutput::from);
    }
}