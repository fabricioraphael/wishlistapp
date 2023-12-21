package com.frb.domain.wishlist;

import com.frb.domain.Identifier;
import com.frb.domain.utils.IdUtils;

import java.util.Objects;

public class WishlistID extends Identifier {
    private final String value;

    private WishlistID(final String value) {
        this.value = Objects.requireNonNull(value, "'wishlistId' cannot be null");
    }

    public static WishlistID unique() {
        return WishlistID.from(IdUtils.uuid());
    }

    public static WishlistID from(final String anId) {
        return new WishlistID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final WishlistID that = (WishlistID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
