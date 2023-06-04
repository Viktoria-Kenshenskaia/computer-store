package com.computer.store.controllers;

import com.computer.store.entities.Product;
import com.computer.store.entities.ProductType;
import com.computer.store.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for managing products.
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Product Api", description = "Product management")
public class ProductController {

    private final ProductService productService;

    /**
     * Add a new product.
     *
     * @param product The product to add.
     * @return ResponseEntity containing the added product.
     * @throws IllegalArgumentException if the 'productType' header value is not one of [monitor, laptop, hardDisk, desktop].
     */
    @PostMapping(headers = "productType")
    @Operation(summary = "Add a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Product> add(@Valid @RequestBody Product product) {
        return ResponseEntity.ok().body(productService.add(product));
    }

    /**
     * Update an existing product.
     *
     * @param id      The ID of the product to update.
     * @param product The updated product data.
     * @return ResponseEntity containing the updated product.
     * @throws IllegalArgumentException if the 'productType' header value is not one of [monitor, laptop, hardDisk, desktop].
     */
    @PutMapping(path = "/{id}", headers = "productType")
    @Operation(summary = "Update a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Product> update(@PathVariable("id") long id,
                                          @Valid @RequestBody Product product) {
        return ResponseEntity.ok().body(productService.update(id, product));
    }

    /**
     * Get all products of a specific type.
     *
     * @param type The type of products to retrieve.
     * @return ResponseEntity containing the list of products.
     */
    @GetMapping
    @Operation(summary = "Get all products by type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Product>> getAllByType(@RequestParam(name = "type") String type) {
        return ResponseEntity.ok().body(productService.getAllByType(ProductType.fromString(type)));
    }

    /**
     * Get a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return ResponseEntity containing the retrieved product.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> get(@PathVariable long id) {
        Optional<Product> expectedProduct = productService.getById(id);
        if (expectedProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id:" + id);
        }
        return ResponseEntity.ok().body(expectedProduct.get());
    }
}
