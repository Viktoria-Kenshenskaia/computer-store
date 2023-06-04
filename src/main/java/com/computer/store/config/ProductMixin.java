package com.computer.store.config;

import com.computer.store.entities.Product;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Mixin class for JSON serialization and deserialization of the Product class hierarchy.
 * <p>
 * Provides information about the subtypes of the Product class.
 */
public abstract class ProductMixin {

    /**
     * Get the subtypes of the Product class.
     *
     * @return Collection of Class objects representing the subtypes of Product.
     */
    public static Collection<Class<?>> getSubtypes() {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder().forPackage(Product.class.getPackageName())
        );
        Set<Class<? extends Product>> subtypes = reflections.getSubTypesOf(Product.class);
        return new ArrayList<>(subtypes);
    }
}
