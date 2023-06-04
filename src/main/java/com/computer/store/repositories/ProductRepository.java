package com.computer.store.repositories;

import com.computer.store.entities.Product;
import com.computer.store.entities.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing products.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Find all products of a specific type.
     *
     * @param type The type of products to retrieve.
     * @return A list of products of the specified type.
     */
    List<Product> findAllByProductType(ProductType type);
}

