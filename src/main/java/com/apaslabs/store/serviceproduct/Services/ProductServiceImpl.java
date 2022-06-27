package com.apaslabs.store.serviceproduct.Services;

import com.apaslabs.store.serviceproduct.entity.Category;
import com.apaslabs.store.serviceproduct.entity.Product;
import com.apaslabs.store.serviceproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> listAllProduct() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("1");
        product.setCreatedAt(new Date());
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product p = getProduct(product.getId());
        if(p == null) {
            return null;
        }
        p.setDescription(product.getDescription());
        p.setPrice(product.getPrice());
        p.setCategory(product.getCategory());
        return this.productRepository.save(p);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product p = getProduct(id);
        if(p == null) {
            return null;
        }
        p.setStatus("0");
        return this.productRepository.save(p);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return this.productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product p = getProduct(id);
        if(p == null) {
            return null;
        }
        Double stock = p.getStock() + quantity;
        p.setStock(stock);
        return this.productRepository.save(p);
    }
}
