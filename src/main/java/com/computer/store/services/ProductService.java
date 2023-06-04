package com.computer.store.services;

import com.computer.store.entities.Product;
import com.computer.store.entities.ProductType;
import com.computer.store.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing products.
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Add a new product.
     *
     * @param product The product to add.
     * @return The added product.
     */
    @Transactional
    public Product add(Product product) {
        return productRepository.save(product);
    }

    /**
     * Update an existing product.
     *
     * @param id      The ID of the product to update.
     * @param product The updated product information.
     * @return The updated product.
     */
    @Transactional
    public Product update(long id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
        }
        return productRepository.save(product);
    }

    /**
     * Get a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The product with the specified ID, if found.
     */
    public Optional<Product> getById(long id) {
        return productRepository.findById(id);
    }

    /**
     * Get all products of a specific type.
     *
     * @param type The type of products to retrieve.
     * @return A list of products of the specified type.
     */
    public List<Product> getAllByType(ProductType type) {
        return productRepository.findAllByProductType(type);
    }
}

