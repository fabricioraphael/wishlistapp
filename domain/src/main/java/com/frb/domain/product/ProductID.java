package com.frb.domain.product;

import com.frb.domain.Identifier;
import com.frb.domain.utils.IdUtils;

import java.util.Objects;

public class ProductID extends Identifier {
    private final String value;

    private ProductID(final String value) {
        this.value = Objects.requireNonNull(value, "'productId' cannot be null");
    }

    public static ProductID from(final String anId) {
        return new ProductID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ProductID that = (ProductID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public String toString() {
        return "ProductID{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
