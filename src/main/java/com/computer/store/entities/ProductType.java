package com.computer.store.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

/**
 * Enumeration representing the types of products.
 */
@Getter
public enum ProductType {
    DESKTOP("desktop"),
    LAPTOP("laptop"),
    MONITOR("monitor"),
    HARD_DISK("hard disk");

    private String type;

    /**
     * Constructs a ProductType with the specified type.
     *
     * @param type The string representation of the product type.
     */
    ProductType(String type) {
        this.type = type;
    }

    /**
     * Converts a string value to the corresponding ProductType enum.
     *
     * @param value The string value representing the product type.
     * @return The ProductType enum corresponding to the given value.
     * @throws IllegalArgumentException if the provided value does not match any valid ProductType.
     */
    @JsonCreator
    public static ProductType fromString(String value) {
        for (ProductType type : ProductType.values()) {
            if (type.type.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid ProductType: " + value);
    }
}
