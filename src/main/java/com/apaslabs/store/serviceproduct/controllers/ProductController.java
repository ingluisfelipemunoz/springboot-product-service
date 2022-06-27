package com.apaslabs.store.serviceproduct.controllers;

import com.apaslabs.store.serviceproduct.Services.ProductService;
import com.apaslabs.store.serviceproduct.Services.ProductServiceImpl;
import com.apaslabs.store.serviceproduct.entity.Category;
import com.apaslabs.store.serviceproduct.entity.Product;
import com.apaslabs.store.serviceproduct.repository.CategoryRepository;
import com.apaslabs.store.serviceproduct.repository.ProductRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> list =  this.productService.listAllProduct();
        System.out.print("list" + list.toString());
        if(list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

}
