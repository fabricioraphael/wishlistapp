package com.frb.application.wishlist.addProduct;

import com.frb.application.UseCaseTest;
import com.frb.application.wishlist.addProduct.AddProductCommand;
import com.frb.application.wishlist.addProduct.AddProductUseCaseImpl;
import com.frb.domain.token.CredentialDataProvider;
import com.frb.domain.wishlist.WishlistGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class AddProductUseCaseTest extends UseCaseTest {

    @InjectMocks
    private AddProductUseCaseImpl useCase;

    @Mock
    private WishlistGateway wishlistGateway;

    @Mock
    private CredentialDataProvider credentialDataProvider;


    @Override
    protected List<Object> getMocks() {
        return List.of(wishlistGateway, credentialDataProvider);
    }

    @Test
    public void givenAValidCommand_whenCallsAddProduct_shouldReturnWishlistId() {
        final var expectedCustomerId = "111";
        final var expectedProductId = "222";

        final var aCommand =
                AddProductCommand.with(expectedCustomerId, expectedProductId);

        when(wishlistGateway.addProduct(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(wishlistGateway, times(1)).addProduct(argThat(aWishlist ->
                        Objects.nonNull(aWishlist.getId())
        ));
    }

    @Test
    public void givenAInvalidCustomerId_whenCallsAddProduct_shouldReturnError() {
        final String expectedCustomerId = null;
        final var expectedProductId = "222";
        final String expectedErrorMessage = "'customerId' cannot be null";

        final var aCommand =
                AddProductCommand.with(expectedCustomerId, expectedProductId);

        final var actualError = Assertions.assertThrows(NullPointerException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualError.getMessage());

        Mockito.verify(wishlistGateway, times(0)).addProduct(any());
    }

    @Test
    public void givenAInvalidProductId_whenCallsAddProduct_shouldReturnError() {
        final var expectedCustomerId = "123";
        final String expectedProductId = null;
        final String expectedErrorMessage = "'productId' cannot be null";

        final var aCommand =
                AddProductCommand.with(expectedCustomerId, expectedProductId);

        final var actualError = Assertions.assertThrows(NullPointerException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualError.getMessage());

        Mockito.verify(wishlistGateway, times(0)).addProduct(any());
    }
}