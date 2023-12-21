package com.frb.domain.customer;

import com.frb.domain.Identifier;
import com.frb.domain.utils.IdUtils;

import java.util.Objects;

public class CustomerID extends Identifier {
    private final String value;

    private CustomerID(final String value) {
        this.value = Objects.requireNonNull(value, "'customerId' cannot be null");
    }

    public static CustomerID from(final String anId) {
        return new CustomerID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CustomerID that = (CustomerID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
