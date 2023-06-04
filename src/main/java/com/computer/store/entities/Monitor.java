package com.computer.store.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a monitor product.
 */
@Entity
@Getter
@Setter
public class Monitor extends Product {

    /**
     * The diagonal size of the monitor.
     */
    private float diagonal;

    /**
     * Sets the product type as "MONITOR" when persisting or updating the entity.
     */
    @Override
    void setProductType() {
        setProductType(ProductType.MONITOR);
    }
}
