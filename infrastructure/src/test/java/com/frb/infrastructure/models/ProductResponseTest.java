package com.frb.infrastructure.models;

import com.frb.infrastructure.JacksonTest;
import com.frb.infrastructure.wishlist.models.AddProductResponse;
import com.frb.infrastructure.wishlist.models.ProductResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

@JacksonTest
public class ProductResponseTest {
        @Autowired
        private JacksonTester<ProductResponse> json;

        @Test
        public void testMarshall() throws Exception {
            final var expectedProductId = "123";

            final var response = new ProductResponse(
                    expectedProductId
            );

            final var actualJson = this.json.write(response);

            Assertions.assertThat(actualJson)
                    .hasJsonPathValue("$.productId", expectedProductId);
        }

        @Test
        public void testUnmarshall() throws Exception {
            final var expectedProductId = "123";

            final var json = """
                {
                  "productId": "%s"
                }
                """.formatted(
                    expectedProductId
                    );

            final var actualJson = this.json.parse(json);

            Assertions.assertThat(actualJson)
                    .hasFieldOrPropertyWithValue("productId", expectedProductId);
        }
}
