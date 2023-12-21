package com.frb.infrastructure.models;

import com.frb.infrastructure.JacksonTest;
import com.frb.infrastructure.wishlist.models.ProductResponse;
import com.frb.infrastructure.wishlist.models.WishlistResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

import java.util.List;

@JacksonTest
public class WishlistResponseTest {
        @Autowired
        private JacksonTester<WishlistResponse> json;

        @Test
        public void testMarshall() throws Exception {
            final var expectedWishlistId = "123";
            final var expectedProducts = List.of(new ProductResponse("234"));

            final var response = new WishlistResponse(
                    expectedWishlistId,
                    expectedProducts
            );

            final var actualJson = this.json.write(response);

            Assertions.assertThat(actualJson)
                    .hasJsonPathValue("$.wishlistId", expectedWishlistId)
                    .hasJsonPathValue("$.products", expectedProducts);
        }

        @Test
        public void testUnmarshall() throws Exception {
            final var expectedWishlistId = "123";
            final var expectedProductId = "234";
            final var expectedProducts = List.of(new ProductResponse("234"));

            final var json = """
                {
                  "wishlistId": "%s",
                  "products": [
                    {"productId": "%s"}
                  ]
                }
                """.formatted(
                    expectedWishlistId,
                    expectedProductId
                    );

            final var actualJson = this.json.parse(json);

            Assertions.assertThat(actualJson)
                    .hasFieldOrPropertyWithValue("wishlistId", expectedWishlistId);
        }
}
