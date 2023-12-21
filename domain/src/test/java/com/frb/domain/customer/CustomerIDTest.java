package com.frb.domain.customer;

import com.frb.domain.wishlist.Wishlist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerIDTest {

    @Test
    public void givenAValidParams_whenCallNewCustomerId_thenInstantiateCustomerIdIdentifier() {
        final var id = "222";
        final var expectedCustomerId = CustomerID.from(id);

        Assertions.assertNotNull(expectedCustomerId);
        Assertions.assertEquals(id, expectedCustomerId.getValue());
    }

    @Test
    public void givenAInvalidParams_whenCallNewCustomerId_thenReturnsError() {
        final var expectedErrorMessage = "'customerId' cannot be null";
        final String id = null;

        final var actualException = Assertions.assertThrows(NullPointerException.class, () -> CustomerID.from(id));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
