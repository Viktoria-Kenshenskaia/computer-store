package com.computer.store.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a laptop product.
 */
@Entity
@Getter
@Setter
public class Laptop extends Product {

    /**
     * The size of the laptop.
     */
    @Enumerated(EnumType.ORDINAL)
    private LaptopSize laptopSize;

    /**
     * Sets the product type to LAPTOP.
     */
    @Override
    void setProductType() {
        setProductType(ProductType.LAPTOP);
    }
}
