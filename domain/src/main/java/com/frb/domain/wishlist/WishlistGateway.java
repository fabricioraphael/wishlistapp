package com.frb.domain.wishlist;

import com.frb.domain.customer.CustomerID;
import com.frb.domain.product.ProductID;

import java.util.List;
import java.util.Optional;

public interface WishlistGateway {

    Wishlist save(Wishlist aWishlist);

    Wishlist addProduct(Wishlist aWishlist);

    Optional<Wishlist> findById(WishlistID anId);

    Optional<Wishlist> findByCustomerId(CustomerID anId);

    void deleteProductByCustomerId(CustomerID aCustomerID, ProductID aProductID);
}
