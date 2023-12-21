package com.frb.infrastructure.configuration.usecases;

import com.frb.application.wishlist.addProduct.AddProductUseCase;
import com.frb.application.wishlist.addProduct.AddProductUseCaseImpl;
import com.frb.application.wishlist.deleteProduct.DeleteProductUseCase;
import com.frb.application.wishlist.deleteProduct.DeleteProductUseCaseImpl;
import com.frb.application.wishlist.retriveAllProducts.RetriveProductsUseCase;
import com.frb.application.wishlist.retriveAllProducts.RetriveProductsUseCaseImpl;
import com.frb.application.wishlist.retriveProduct.RetriveProductUseCase;
import com.frb.application.wishlist.retriveProduct.RetriveProductUseCaseImpl;
import com.frb.domain.wishlist.WishlistGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WishlistUseCaseConfig {
    private final WishlistGateway wishlistGateway;

    public WishlistUseCaseConfig(final WishlistGateway wishlistGateway) {
        this.wishlistGateway = wishlistGateway;
    }

    @Bean
    public AddProductUseCase addProductUseCase() {
        return new AddProductUseCaseImpl(wishlistGateway);
    }

    @Bean
    public RetriveProductsUseCase retriveProductsUseCase() {
        return new RetriveProductsUseCaseImpl(wishlistGateway);
    }

    @Bean
    public RetriveProductUseCase retriveProductUseCase() {
        return new RetriveProductUseCaseImpl(wishlistGateway);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase() {
        return new DeleteProductUseCaseImpl(wishlistGateway);
    }
}
