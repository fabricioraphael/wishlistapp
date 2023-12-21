package com.frb.infrastructure.wishlist.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends MongoRepository<WishlistEntity, String> {

    List<WishlistEntity> findByCustomerId(String customerId);
}
