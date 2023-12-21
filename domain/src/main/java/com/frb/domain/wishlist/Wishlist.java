package com.frb.domain.wishlist;

import com.frb.domain.AggregateRoot;
import com.frb.domain.customer.CustomerID;
import com.frb.domain.product.Product;
import com.frb.domain.product.ProductID;
import com.frb.domain.validation.ValidationHandler;

import java.util.*;
import java.util.function.Predicate;

public class Wishlist extends AggregateRoot<WishlistID> implements Cloneable {
    private CustomerID customerId;

    private List<Product> products;

    private Wishlist(
            final WishlistID anId,
            final CustomerID aCustomerId,
            final List<Product> products

    ) {
        super(anId);
        this.customerId = Objects.requireNonNull(aCustomerId, "'customerId' cannot be null");
        this.products = new ArrayList<>(products == null ? Collections.emptyList() : products);
    }

    public static Wishlist newWishlist(
            final CustomerID aCustomerId,
            final List<Product> aProducts
    ) {
        final var id = WishlistID.unique();
        return new Wishlist(id, aCustomerId, aProducts);
    }

    public static Wishlist with(
            final WishlistID anId,
            final CustomerID aCustomerId,
            final List<Product> aProducts
    ) {
        return new Wishlist(
                anId,
                aCustomerId,
                aProducts
        );
    }

    public static Wishlist with(final Wishlist aWishlist) {
        return with(
                aWishlist.getId(),
                aWishlist.customerId,
                aWishlist.products
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new WishlistValidator(this, handler).validate();
    }


    public CustomerID getCustomerId() {
        return customerId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(final Product product) {
        if (product == null) {
            return;
        }
        this.products.add(product);
    }

    public void addProduct(final Product product, final ValidationHandler validationHandler) {
        if (product == null) {
            return;
        }
        this.products.add(product);
        this.validate(validationHandler);
    }

    public Optional<Product> retriveProduct(final ProductID productId) {
        return this.products.stream()
                .filter(prod -> prod.getId().getValue().equalsIgnoreCase(productId.getValue()))
                .findFirst();
    }

    public void removeProductById(final ProductID productId) {
        Predicate<Product> isQualifiedToRemove = prod -> prod.getId().getValue().equalsIgnoreCase(productId.getValue());
        this.products.removeIf(isQualifiedToRemove);
    }

    @Override
    public Wishlist clone() {
        try {
            return (Wishlist) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "id='" + id.getValue() + '\'' +
                "products='" + products + '\'' +
                '}';
    }
}
