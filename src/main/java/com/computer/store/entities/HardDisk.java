package com.computer.store.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a hard disk product.
 */
@Entity
@Getter
@Setter
public class HardDisk extends Product {

    /**
     * The capacity of the hard disk in gigabytes.
     */
    private int capacity;

    /**
     * Sets the product type to HARD_DISK.
     */
    @Override
    void setProductType() {
        setProductType(ProductType.HARD_DISK);
    }
}
