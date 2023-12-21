package com.frb.infrastructure.models;

import com.frb.infrastructure.JacksonTest;
import com.frb.infrastructure.wishlist.models.AddProductRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;


@JacksonTest
public class AddProductRequestTest {

    @Autowired
    private JacksonTester<AddProductRequest> json;

    @Test
    public void testMarshall() throws Exception {
        final var expectedProductId = "123123";

        final var request =
                new AddProductRequest(expectedProductId);

        final var actualJson = this.json.write(request);

        Assertions.assertThat(actualJson)
                .hasJsonPathValue("$.productId", expectedProductId);
    }

    @Test
    public void testUnmarshall() throws Exception {
        final var expectedProductId = "123123";

        final var json = """
                {
                  "productId": %s
                }
                """.formatted(expectedProductId);

        final var actualJson = this.json.parse(json);

        Assertions.assertThat(actualJson)
                .hasFieldOrPropertyWithValue("productId", expectedProductId);
    }
}
