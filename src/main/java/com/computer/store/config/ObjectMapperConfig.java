package com.computer.store.config;

import com.computer.store.entities.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * Configuration class for customizing the ObjectMapper used for JSON serialization and deserialization.
 */
@Configuration
public class ObjectMapperConfig {

    /**
     * Creates and configures the ObjectMapper bean.
     *
     * @return the configured ObjectMapper instance
     */
    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // Register subtypes and Mixin classes
        Collection<Class<?>> productSubtypes = ProductMixin.getSubtypes();
        objectMapper.registerSubtypes(productSubtypes.toArray(new Class<?>[0]));
        objectMapper.addMixIn(Product.class, ProductMixin.class);

        // Register custom deserializer for Product class
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Product.class, new ProductDeserializer());
        objectMapper.registerModule(module);

        return objectMapper;
    }
}
