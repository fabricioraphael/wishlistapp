package com.frb.domain.wishlist;

import com.frb.domain.customer.CustomerID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WishlistIDTest {

    @Test
    public void givenAValidParams_whenCallNewWishlistID_thenInstantiateWishlistIDIdentifier() {
        final var id = "222";
        final var expectedWishlistID = WishlistID.from(id);

        Assertions.assertNotNull(expectedWishlistID);
        Assertions.assertEquals(id, expectedWishlistID.getValue());
    }

    @Test
    public void givenAInvalidParams_whenCallNewWishlistID_thenReturnsError() {
        final var expectedErrorMessage = "'wishlistId' cannot be null";
        final String id = null;

        final var actualException = Assertions.assertThrows(NullPointerException.class, () -> WishlistID.from(id));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
