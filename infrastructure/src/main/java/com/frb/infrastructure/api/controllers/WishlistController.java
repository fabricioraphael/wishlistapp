package com.frb.infrastructure.api.controllers;

import com.frb.application.wishlist.addProduct.AddProductCommand;
import com.frb.application.wishlist.addProduct.AddProductOutput;
import com.frb.application.wishlist.addProduct.AddProductUseCase;
import com.frb.application.wishlist.deleteProduct.DeleteProductCommand;
import com.frb.application.wishlist.deleteProduct.DeleteProductUseCase;
import com.frb.application.wishlist.retriveAllProducts.RetriveProductsCommand;
import com.frb.application.wishlist.retriveAllProducts.RetriveProductsUseCase;
import com.frb.application.wishlist.retriveProduct.RetriveProductCommand;
import com.frb.application.wishlist.retriveProduct.RetriveProductUseCase;
import com.frb.domain.token.CredentialData;
import com.frb.domain.validation.handler.Notification;
import com.frb.infrastructure.api.WishlistAPI;
import com.frb.infrastructure.shared.CredentialDataProviderImpl;
import com.frb.infrastructure.wishlist.models.AddProductRequest;
import com.frb.infrastructure.wishlist.models.ProductResponse;
import com.frb.infrastructure.wishlist.models.WishlistResponse;
import com.frb.infrastructure.wishlist.presenters.WishlistApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class WishlistController implements WishlistAPI {

    private final AddProductUseCase addProductUseCase;
    private final RetriveProductsUseCase retriveProductsUseCase;

    private final RetriveProductUseCase retriveProductUseCase;

    private final CredentialDataProviderImpl credentialDataProviderImpl;

    private final DeleteProductUseCase deleteProductUseCase;

    public WishlistController(final AddProductUseCase addProductUseCase,
                              final CredentialDataProviderImpl credentialDataProviderImpl,
                              final RetriveProductsUseCase retriveProductsUseCase,
                              final RetriveProductUseCase retriveProductUseCase,
                              final DeleteProductUseCase deleteProductUseCase) {
        this.addProductUseCase = Objects.requireNonNull(addProductUseCase);
        this.credentialDataProviderImpl = Objects.requireNonNull(credentialDataProviderImpl);
        this.retriveProductsUseCase = Objects.requireNonNull(retriveProductsUseCase);
        this.retriveProductUseCase = Objects.requireNonNull(retriveProductUseCase);
        this.deleteProductUseCase = Objects.requireNonNull(deleteProductUseCase);
    }

    @Override
    public ResponseEntity<?> addProduct(AddProductRequest input) {
        CredentialData credentialData = credentialDataProviderImpl.getCredentialData();

        final var aCommand = AddProductCommand.with(
                credentialData.customerId(),
                input.productId()
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<AddProductOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/wishlists/" + output.id())).body(output);

        return this.addProductUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public WishlistResponse retriveWishlistWithProducts() {
        CredentialData credentialData = credentialDataProviderImpl.getCredentialData();

        final var aCommand = RetriveProductsCommand.with(
                credentialData.customerId()
        );

        return WishlistApiPresenter.present(this.retriveProductsUseCase.execute(aCommand));
    }

    @Override
    public ProductResponse retriveWishlistProduct(String id) {
        CredentialData credentialData = credentialDataProviderImpl.getCredentialData();

        final var aCommand = RetriveProductCommand.with(
                credentialData.customerId(),
                id
        );

        return WishlistApiPresenter.present(this.retriveProductUseCase.execute(aCommand));
    }

    @Override
    public void deleteProductFromWishlist(String id) {
        CredentialData credentialData = credentialDataProviderImpl.getCredentialData();

        final var aCommand = DeleteProductCommand.with(
                credentialData.customerId(),
                id
        );
        this.deleteProductUseCase.execute(aCommand);
    }
}
