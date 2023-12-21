package com.frb.application.wishlist.retriveAllProduct;

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

public class RetriveProductsUseCaseTest extends UseCaseTest {

    @InjectMocks
    private RetriveProductsUseCaseImpl useCase;

    @Mock
    private WishlistGateway wishlistGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(wishlistGateway);
    }

    @Test
    public void givenAValidCustomerId_whenCallsRetriveProducts_shouldReturnWishlistWithProducts() {
        final var expectedCustomerId = "111";

        final var products = List.of(
                Product.with(ProductID.from("123")),
                Product.with(ProductID.from("234")),
                Product.with(ProductID.from("345")));

        final var actualWishlist = Wishlist.newWishlist(CustomerID.from(expectedCustomerId), products);

        final var aCommand = RetriveProductsCommand.with(expectedCustomerId);

        when(wishlistGateway.findByCustomerId(any()))
                .thenReturn(Optional.of(actualWishlist));

        final var actualOutput = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());
        Assertions.assertNotNull(actualOutput.products());
        Assertions.assertEquals(products.size(), actualOutput.products().size());

        Mockito.verify(wishlistGateway, times(1)).findByCustomerId(argThat(customerID ->
                        Objects.nonNull(customerID)
                && Objects.nonNull(customerID.getValue())
        ));
    }

    @Test
    public void givenAValidCustomerId_whenCallsRetriveProductsAndItsEmpty_shouldReturnWishlist() {
        final var expectedCustomerId = "111";

        final var actualWishlist = Wishlist.newWishlist(CustomerID.from(expectedCustomerId), new ArrayList<>());

        final var aCommand = RetriveProductsCommand.with(expectedCustomerId);

        when(wishlistGateway.findByCustomerId(any()))
                .thenReturn(Optional.of(actualWishlist));

        final var actualOutput = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());
        Assertions.assertNotNull(actualOutput.products());
        Assertions.assertEquals(0, actualOutput.products().size());

        Mockito.verify(wishlistGateway, times(1)).findByCustomerId(argThat(customerID ->
                Objects.nonNull(customerID)
                        && Objects.nonNull(customerID.getValue())
        ));
    }

    @Test
    public void givenAInvalidCustomerId_whenCallsRetriveProducts_shouldReturnNotFoundException() {
        final var expectedCustomerId = "111";
        final var expectedErrorMessage = "Wishlist was not found";

        final var aCommand = RetriveProductsCommand.with(expectedCustomerId);

        when(wishlistGateway.findByCustomerId(any())).thenThrow(NotFoundException.with(new Error(expectedErrorMessage)));

        final var actualOutput = Assertions.assertThrows(NotFoundException.class, () -> {
            useCase.execute(aCommand);
        });

        Assertions.assertNotNull(actualOutput);
        Assertions.assertEquals(expectedErrorMessage, actualOutput.getMessage());

        verify(wishlistGateway).findByCustomerId(eq(CustomerID.from(expectedCustomerId)));
    }
}