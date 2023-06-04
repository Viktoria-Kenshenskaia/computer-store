package com.computer.store.config;

import com.computer.store.entities.Product;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

/**
 * Custom deserializer for the Product class.
 * <p>
 * It handles deserialization of Product objects based on the "productType" header value in the request.
 */
public class ProductDeserializer extends StdDeserializer<Product> {

    /**
     * Default constructor.
     * Calls the parameterized constructor with a null value for the value class.
     */
    public ProductDeserializer() {
        this(null);
    }

    /**
     * Parameterized constructor.
     *
     * @param vc the value class handled by this deserializer
     */
    public ProductDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * Deserializes the JSON into a Product object.
     * <p>
     * Determines the subtype of the Product based on the "productType" header in the request.
     *
     * @param p    the JSON parser
     * @param ctxt the deserialization context
     * @return the deserialized Product object
     * @throws IOException if an I/O error occurs during deserialization
     */
    @Override
    public Product deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        HttpServletRequest request = ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        String productType = request.getHeader("productType");

        Class<? extends Product> subtype = getSubtypeByProductType(productType);

        return codec.treeToValue(node, subtype);
    }

    /**
     * Retrieves the subtype class based on the productType value.
     *
     * @param productType the productType value from the request header
     * @return the corresponding subtype class of Product
     * @throws IllegalArgumentException if the productType value is invalid or does not match any subtype
     */
    private Class<? extends Product> getSubtypeByProductType(String productType) {
        Collection<Class<?>> productSubtypes = ProductMixin.getSubtypes();
        for (Class<?> subtype : productSubtypes) {
            if (subtype.getSimpleName().equalsIgnoreCase(productType)) {
                return (Class<? extends Product>) subtype;
            }
        }

        throw new IllegalArgumentException("Invalid product type: " + productType);
    }
}
