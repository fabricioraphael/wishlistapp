package com.frb.application.wishlist.retriveProduct;

import com.frb.application.UseCaseTest;
import com.frb.application.wishlist.addProduct.AddProductCommand;
import com.frb.application.wishlist.addProduct.AddProductUseCaseImpl;
import com.frb.application.wishlist.retriveProduct.RetriveProductCommand;
import com.frb.application.wishlist.retriveProduct.RetriveProductUseCaseImpl;
import com.frb.domain.customer.CustomerID;
import com.frb.domain.exceptions.NotFoundException;
import com.frb.domain.product.Product;
import com.frb.domain.product.ProductID;
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

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class RetriveProductUseCaseTest extends UseCaseTest {

    @InjectMocks
    private RetriveProductUseCaseImpl useCase;

    @Mock
    private WishlistGateway wishlistGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(wishlistGateway);
    }

    @Test
    public void givenAValidId_whenCallsRetriveProduct_shouldReturnProduct() {
        final var expectedCustomerId = "111";
        final var expectedProductId = "222";

        final var actualWishlist = Wishlist.newWishlist(CustomerID.from(expectedCustomerId), List.of(Product.with(ProductID.from(expectedProductId))));

        final var aCommand =
                RetriveProductCommand.with(expectedCustomerId, expectedProductId);

        when(wishlistGateway.findByCustomerId(any()))
                .thenReturn(Optional.of(actualWishlist));

        final var actualOutput = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.productId());
        Assertions.assertEquals(expectedProductId, actualOutput.productId());

        Mockito.verify(wishlistGateway, times(1)).findByCustomerId(argThat(product ->
                        Objects.nonNull(product)
        ));
    }

    @Test
    public void givenAInvalidId_whenCallsRetriveProductAndDoesNotExists_shouldReturnNotFoundException() {
        final var expectedCustomerId = "111";
        final var expectedProductId = "222";
        final var expectedErrorMessage = "Product not found on wishlist";

        final var aCommand = RetriveProductCommand.with(expectedCustomerId, expectedProductId);

        when(wishlistGateway.findByCustomerId(any())).thenReturn(Optional.empty());

        final var actualOutput = Assertions.assertThrows(NotFoundException.class, () -> {
            useCase.execute(aCommand);
        });

        Assertions.assertNotNull(actualOutput);
        Assertions.assertEquals(expectedErrorMessage, actualOutput.getMessage());

        verify(wishlistGateway).findByCustomerId(eq(CustomerID.from(expectedCustomerId)));
    }
}