package com.frb.application.wishlist.deleteProduct;

import com.frb.application.UseCaseTest;
import com.frb.application.wishlist.retriveAllProducts.RetriveProductsCommand;
import com.frb.application.wishlist.retriveAllProducts.RetriveProductsUseCaseImpl;
import com.frb.domain.customer.CustomerID;
import com.frb.domain.exceptions.NotFoundException;
import com.frb.domain.product.Product;
import com.frb.domain.product.ProductID;
import com.frb.domain.validation.Error;
import com.frb.domain.wishlist.Wishlist;
import com.frb.domain.wishlist.WishlistGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class DeleteProductUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DeleteProductUseCaseImpl useCase;

    @Mock
    private WishlistGateway wishlistGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(wishlistGateway);
    }

    @Test
    public void givenAValidProductId_whenCallsDeleteProduct_shouldReturnWishlistWithoutTheProduct() {
        final var expectedCustomerId = "111";
        final var expectedProductId = "111";

        final var aCommand = DeleteProductCommand.with(expectedCustomerId, expectedProductId);

        doNothing()
                .when(wishlistGateway).deleteProductByCustomerId(any(), any());

        Assertions.assertDoesNotThrow(() -> useCase.execute(aCommand));

        verify(wishlistGateway).deleteProductByCustomerId(eq(CustomerID.from(expectedCustomerId)), eq(ProductID.from(expectedProductId)));
    }
}