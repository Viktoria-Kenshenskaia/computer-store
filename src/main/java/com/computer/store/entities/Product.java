package com.computer.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * An abstract class representing a product.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(length = 30)
    private String serialNumber;

    private String manufacturer;

    @Column(columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal price;

    @Min(value = 0, message = "Value cannot ne less then 0.")
    private int quantityInStock;

    /**
     * The type of the product.
     */
    @Enumerated(value = EnumType.STRING)
    private ProductType productType;

    /**
     * Sets the product type. This method should be implemented by subclasses to specify the product type.
     */
    @PrePersist
    @PreUpdate
    abstract void setProductType();
}
