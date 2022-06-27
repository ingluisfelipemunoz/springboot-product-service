package com.apaslabs.store.serviceproduct;

import com.apaslabs.store.serviceproduct.entity.Category;
import com.apaslabs.store.serviceproduct.entity.Product;
import com.apaslabs.store.serviceproduct.repository.CategoryRepository;
import com.apaslabs.store.serviceproduct.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void whenFindByCategory_thenReturnListProduct() {
    Product p1 = Product.builder()
            .name("acer laptop")
           .category(Category.builder().id(1L).build())
            .description("")
            .stock(10.0)
            .price(20.0)
            .status("1").createdAt(new Date()).build();

    productRepository.save(p1);
    List<Product> founds = productRepository.findByCategory(p1.getCategory());
    Assertions.assertThat(founds.size()).isEqualTo(3);

    }
}
