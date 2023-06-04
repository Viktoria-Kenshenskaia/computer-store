package com.computer.store.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * Represents the size of a laptop.
 */
@Getter
public enum LaptopSize {

    INCH_13(13),
    INCH_14(14),
    INCH_15(15),
    INCH_17(17);

    private final int size;

    /**
     * Constructs a LaptopSize enum with the specified size.
     *
     * @param size the size of the laptop
     */
    LaptopSize(int size) {
        this.size = size;
    }

    /**
     * Returns the LaptopSize enum based on the specified size value.
     *
     * @param value the size value of the laptop
     * @return the corresponding LaptopSize enum
     * @throws IllegalArgumentException if the specified value is invalid
     */
    @JsonCreator
    public static LaptopSize fromString(int value) {
        for (LaptopSize size : LaptopSize.values()) {
            if (size.getSize() == value) {
                return size;
            }
        }
        throw new IllegalArgumentException("Invalid Laptop Size: " + value);
    }

    @JsonValue
    public String toString() {
        return String.valueOf(size);
    }
}
