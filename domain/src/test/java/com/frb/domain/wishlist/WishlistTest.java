package com.frb.domain.wishlist;

import com.frb.domain.customer.CustomerID;
import com.frb.domain.exceptions.DomainException;
import com.frb.domain.product.Product;
import com.frb.domain.product.ProductID;
import com.frb.domain.validation.handler.Notification;
import com.frb.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class WishlistTest {

    @Test
    public void givenAValidParams_whenCallNewWishlist_thenInstantiateAWishlist() {
        final var expectedCustomerId = CustomerID.from("222");

        final var actualWishlist = Wishlist.newWishlist(expectedCustomerId, null);

        Assertions.assertNotNull(actualWishlist);
        Assertions.assertNotNull(actualWishlist.getId());
        Assertions.assertNotNull(actualWishlist.getProducts());
        Assertions.assertEquals(expectedCustomerId, actualWishlist.getCustomerId());
        Assertions.assertEquals(0, actualWishlist.getProducts().size());
    }

    @Test
    public void givenAValidParamsWithProducts_whenCallNewWishlist_thenInstantiateAWishlistWithProducts() {
        final var expectedCustomerId = CustomerID.from("222");
        var products = new ArrayList<>(List.of(
                Product.with(ProductID.from("123")),
                Product.with(ProductID.from("567"))));

        final var actualWishlist = Wishlist.newWishlist(expectedCustomerId, products);

        Assertions.assertNotNull(actualWishlist);
        Assertions.assertNotNull(actualWishlist.getId());
        Assertions.assertNotNull(actualWishlist.getProducts());
        Assertions.assertEquals(expectedCustomerId, actualWishlist.getCustomerId());
        Assertions.assertEquals(2, actualWishlist.getProducts().size());
    }

    @Test
    public void givenAnInvalidCustomerId_whenCallNewWishlistAndValidate_thenShouldReceiveError() {
        final var expectedCustomerId = CustomerID.from("222");
        final var expectedErrorMessage = "'customerId' cannot be null";

        final var actualException = Assertions.assertThrows(NullPointerException.class, () -> Wishlist.newWishlist(null, null));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAddProductsBeyondLimit_whenCallNewWishlistAnd_thenShouldReceiveNotificationWithError() {
        final var expectedCustomerId = CustomerID.from("222");
        final var expectedErrorMessage = "the quantity of 'products' cannot be greater than 20.";

        var products = new ArrayList<Product>();

        IntStream.range(0, 20).forEach(i -> products.add(Product.with(ProductID.from("id_"+i))));

        var actualWishlist = Wishlist.newWishlist(expectedCustomerId, products);

        Assertions.assertDoesNotThrow(() -> actualWishlist.validate(new ThrowsValidationHandler()));

        final var notification = Notification.create();

        actualWishlist.addProduct(Product.with(ProductID.from("new-prod")), notification);

        Assertions.assertTrue(notification.hasError());
        Assertions.assertEquals(1, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().get(0).message());
    }

    @Test
    public void givenAddProductsBeyondLimit_whenCallNewWishlistAndValidate_thenShouldReceiveError() {
        final var expectedCustomerId = CustomerID.from("222");
        final var expectedErrorMessage = "the quantity of 'products' cannot be greater than 20.";

        var products = new ArrayList<Product>();

        IntStream.range(0, 21).forEach(i -> products.add(Product.with(ProductID.from("id_"+i))));

        var actualWishlist = Wishlist.newWishlist(expectedCustomerId, products);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualWishlist.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenProductId_whenCallRetriveProduct_thenReturnsTheProduct() {
        final var expectedCustomerId = CustomerID.from("222");
        final var expectedProductId = ProductID.from("234");

        var products = new ArrayList<>(List.of(
                Product.with(ProductID.from("123")),
                Product.with(expectedProductId),
                Product.with(ProductID.from("345"))));

        final var actualWishlist = Wishlist.newWishlist(expectedCustomerId, products);

        final var findedProduct = actualWishlist.retriveProduct(expectedProductId);

        Assertions.assertNotNull(actualWishlist);
        Assertions.assertTrue(findedProduct.isPresent());
        Assertions.assertNotNull(findedProduct.get());
        Assertions.assertNotNull(actualWishlist.getId());
        Assertions.assertNotNull(actualWishlist.getProducts());
        Assertions.assertEquals(expectedCustomerId, actualWishlist.getCustomerId());
        Assertions.assertEquals(3, actualWishlist.getProducts().size());

        Assertions.assertEquals(expectedProductId.getValue(), findedProduct.get().getId().getValue());
    }

    @Test
    public void givenProductId_whenCallRemoveProduct_thenRemoveProductFromList() {
        final var expectedCustomerId = CustomerID.from("222");
        final var expectedProductId = ProductID.from("234");

        var products = new ArrayList<>(List.of(
                Product.with(ProductID.from("123")),
                Product.with(expectedProductId),
                Product.with(ProductID.from("345"))));

        final var actualWishlist = Wishlist.newWishlist(expectedCustomerId, products);

        Assertions.assertEquals(3, actualWishlist.getProducts().size());

        actualWishlist.removeProductById(expectedProductId);

        Assertions.assertEquals(2, actualWishlist.getProducts().size());
    }
}
