package com.example.mycloud.service;

import com.example.mycloud.model.Product;
import com.example.mycloud.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void getAllShouldReturnProducts() {
        when(productRepository.findAll()).thenReturn(List.of(new Product(1L, "test", null, null, null, null, null, null)));
        List<Product> result = productService.getAll();
        assertThat(result).hasSize(1);
    }

    @Test
    void saveShouldCallRepository() {
        Product p = new Product();
        productService.save(p);
        verify(productRepository).save(p);
    }

    @Test
    void deleteShouldCallRepository() {
        productService.delete(42L);
        verify(productRepository).deleteById(42L);
    }
}