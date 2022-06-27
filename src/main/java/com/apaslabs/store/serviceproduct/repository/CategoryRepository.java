package com.apaslabs.store.serviceproduct.repository;

import com.apaslabs.store.serviceproduct.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
