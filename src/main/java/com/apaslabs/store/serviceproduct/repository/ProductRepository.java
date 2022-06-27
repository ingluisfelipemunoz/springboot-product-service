package com.apaslabs.store.serviceproduct.repository;

import com.apaslabs.store.serviceproduct.entity.Category;
import com.apaslabs.store.serviceproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);
}
