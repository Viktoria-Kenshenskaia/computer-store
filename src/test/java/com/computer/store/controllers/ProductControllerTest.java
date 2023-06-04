package com.computer.store.controllers;

import com.computer.store.config.ObjectMapperConfig;
import com.computer.store.entities.Desktop;
import com.computer.store.entities.DesktopFormFactor;
import com.computer.store.entities.HardDisk;
import com.computer.store.entities.Laptop;
import com.computer.store.entities.LaptopSize;
import com.computer.store.entities.Monitor;
import com.computer.store.entities.Product;
import com.computer.store.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@Import(ObjectMapperConfig.class)
public class ProductControllerTest {

    private final String baseUri = "/products";
    @MockBean
    private ProductService mockProductService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @ParameterizedTest
    @MethodSource("provideValidProduct")
    @SneakyThrows
    public void shouldAddNewProduct_whenValidRequest_thenResponseIs200(Product product, String headerType) {
        when(mockProductService.add(any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post(baseUri)
                        .header("productType", headerType)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(product))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.quantityInStock").value(product.getQuantityInStock()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andExpect(jsonPath("$.serialNumber").value(product.getSerialNumber()));
    }

    @Test
    @SneakyThrows
    public void shouldReturnProductById_whenProductExist_thenResponseIs200() {
        final long id = 1;
        Product monitor = new Monitor();
        monitor.setId(id);
        when(mockProductService.getById(id)).thenReturn(Optional.of(monitor));

        mockMvc.perform(MockMvcRequestBuilders.get(baseUri + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(monitor.getId()));
    }

    @ParameterizedTest
    @MethodSource("provideValidProduct")
    @SneakyThrows
    public void shouldUpdateProduct_whenProductExist_thenResponseIs200(Product product, String headerType) {
        when(mockProductService.update(any(Long.class), any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.put(baseUri + "/{id}", 1)
                        .header("productType", headerType)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(product))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.quantityInStock").value(product.getQuantityInStock()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andExpect(jsonPath("$.serialNumber").value(product.getSerialNumber()));
    }

    @Test
    @SneakyThrows
    public void shouldReturnAllProductsByType_thenResponseIs200() {
        when(mockProductService.getAllByType(any())).thenReturn(List.of(
                new Monitor(),
                new Monitor()
        ));

        mockMvc.perform(MockMvcRequestBuilders.get(baseUri)
                        .param("type", "monitor")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    static Stream<Arguments> provideValidProduct() {
        Monitor monitor = new Monitor();
        monitor.setPrice(BigDecimal.valueOf(49.9));
        monitor.setSerialNumber("00121");
        monitor.setQuantityInStock(10);
        monitor.setDiagonal(42);
        Desktop desktop = new Desktop();
        desktop.setPrice(BigDecimal.valueOf(149.9));
        desktop.setSerialNumber("00122");
        desktop.setQuantityInStock(15);
        desktop.setFormFactor(DesktopFormFactor.MONOBLOCK);
        Laptop laptop = new Laptop();
        laptop.setPrice(BigDecimal.valueOf(249.9));
        laptop.setSerialNumber("00123");
        laptop.setQuantityInStock(20);
        laptop.setLaptopSize(LaptopSize.INCH_13);
        HardDisk hardDisk = new HardDisk();
        hardDisk.setPrice(BigDecimal.valueOf(349.9));
        hardDisk.setSerialNumber("00124");
        hardDisk.setQuantityInStock(25);
        hardDisk.setCapacity(2000);
        return Stream.of(
                Arguments.of(monitor, "monitor"),
                Arguments.of(desktop, "desktop"),
                Arguments.of(laptop, "laptop"),
                Arguments.of(hardDisk, "hardDisk")
        );
    }
}
