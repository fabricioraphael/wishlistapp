package com.frb.domain.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductIDTest {

    @Test
    public void givenAValidParams_whenCallNewProductID_thenInstantiateProductIDIdentifier() {
        final var id = "222";
        final var expectedProductID = ProductID.from(id);

        Assertions.assertNotNull(expectedProductID);
        Assertions.assertEquals(id, expectedProductID.getValue());
    }

    @Test
    public void givenAInvalidParams_whenCallNewProductID_thenReturnsError() {
        final var expectedErrorMessage = "'productId' cannot be null";
        final String id = null;

        final var actualException = Assertions.assertThrows(NullPointerException.class, () -> ProductID.from(id));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
