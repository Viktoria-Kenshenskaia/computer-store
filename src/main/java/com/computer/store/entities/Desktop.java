package com.computer.store.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;


/**
 * Represents a desktop computer.
 */
@Entity
@Getter
@Setter
public class Desktop extends Product {

    /**
     * The form factor of the desktop computer.
     */
    @Enumerated(value = EnumType.STRING)
    private DesktopFormFactor formFactor;

    /**
     * Sets the product type to {@link ProductType#DESKTOP}.
     */
    @Override
    void setProductType() {
        setProductType(ProductType.DESKTOP);
    }
}
