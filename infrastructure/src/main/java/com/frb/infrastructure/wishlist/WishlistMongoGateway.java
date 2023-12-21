package com.frb.infrastructure.wishlist;

import com.frb.domain.customer.CustomerID;
import com.frb.domain.product.ProductID;
import com.frb.domain.wishlist.Wishlist;
import com.frb.domain.wishlist.WishlistGateway;
import com.frb.domain.wishlist.WishlistID;
import com.frb.infrastructure.wishlist.persistence.WishlistEntity;
import com.frb.infrastructure.wishlist.persistence.WishlistRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WishlistMongoGateway implements WishlistGateway {

    private final WishlistRepository repository;

    public WishlistMongoGateway(final WishlistRepository repository) {
        this.repository = repository;
    }

    @Override
    public Wishlist save(Wishlist aWishlist) {
        return this.repository.save(WishlistEntity.from(aWishlist)).toAggregate();
    }

    @Override
    public Wishlist addProduct(Wishlist aWishlist) {
        return this.repository.save(WishlistEntity.from(aWishlist)).toAggregate();
    }

    @Override
    public Optional<Wishlist> findById(WishlistID anId) {
        return this.repository.findById(anId.getValue())
                .map(WishlistEntity::toAggregate);
    }

    @Override
    public Optional<Wishlist> findByCustomerId(CustomerID anId) {

        var wishlists = this.repository.findByCustomerId(anId.getValue());

        if (wishlists.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(wishlists.stream()
                    .map(WishlistEntity::toAggregate).findFirst().get());
        }
    }

    @Override
    public void deleteProductByCustomerId(CustomerID aCustomerID, ProductID aProductID) {
        var wishlists = this.findByCustomerId(aCustomerID);

        if (wishlists.isPresent()) {
            var actualWishlist = wishlists.get();

            actualWishlist.removeProductById(aProductID);

            this.save(actualWishlist);
        }
    }
}
