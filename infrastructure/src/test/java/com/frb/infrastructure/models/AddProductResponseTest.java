package com.frb.infrastructure.models;

import com.frb.infrastructure.JacksonTest;
import com.frb.infrastructure.wishlist.models.AddProductResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

import java.time.LocalDate;

@JacksonTest
public class AddProductResponseTest {
        @Autowired
        private JacksonTester<AddProductResponse> json;

        @Test
        public void testMarshall() throws Exception {
            final var expectedWishlistId = "123";

            final var response = new AddProductResponse(
                    expectedWishlistId
            );

            final var actualJson = this.json.write(response);

            Assertions.assertThat(actualJson)
                    .hasJsonPathValue("$.id", expectedWishlistId);
        }

        @Test
        public void testUnmarshall() throws Exception {
            final var expectedWishlistId = "123";

            final var json = """
                {
                  "id": "%s"
                }
                """.formatted(
                        expectedWishlistId
                    );

            final var actualJson = this.json.parse(json);

            Assertions.assertThat(actualJson)
                    .hasFieldOrPropertyWithValue("id", expectedWishlistId);
        }
}
