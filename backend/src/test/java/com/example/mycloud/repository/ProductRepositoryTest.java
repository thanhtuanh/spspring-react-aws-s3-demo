package com.example.mycloud.repository;

import com.example.mycloud.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveAndFindAll() {
        Product product = new Product(null, "Test", "url", "file.jpg", "image/jpeg", 123L, 100, 200);
        productRepository.save(product);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getTitle()).isEqualTo("Test");
    }
}