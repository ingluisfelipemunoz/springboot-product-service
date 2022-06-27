package com.apaslabs.store.serviceproduct.Services;

import com.apaslabs.store.serviceproduct.entity.Category;
import com.apaslabs.store.serviceproduct.entity.Product;
import com.apaslabs.store.serviceproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProductService {
    List<Product> listAllProduct();
    Product getProduct(Long id);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(Long id);
    List<Product> findByCategory(Category category);
    Product updateStock(Long id, Double quantity);
}
