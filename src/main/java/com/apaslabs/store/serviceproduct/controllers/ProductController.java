package com.apaslabs.store.serviceproduct.controllers;

import com.apaslabs.store.serviceproduct.Services.ProductService;
import com.apaslabs.store.serviceproduct.Services.ProductServiceImpl;
import com.apaslabs.store.serviceproduct.entity.Category;
import com.apaslabs.store.serviceproduct.entity.Product;
import com.apaslabs.store.serviceproduct.repository.CategoryRepository;
import com.apaslabs.store.serviceproduct.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam(name = "categoryId", required = false) Long categoryId) {
        List<Product> list =  new ArrayList<>();
        if(null == categoryId) {
            list = this.productService.listAllProduct();
        } else {
            list = this.productService.findByCategory(Category.builder().id(categoryId).build());
        }

        System.out.print("list" + list.toString());
        if(list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(name="id", required = true) Long id) {
        Product p = this.productService.getProduct(id);
        if(null == p) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(p);
        }
    }

    @PostMapping
    public ResponseEntity<Product> save(@Valid @RequestBody Product dto, BindingResult result) {
        if(result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Product res =  this.productService.createProduct(dto);
        return (null == res) ? ResponseEntity.notFound().build() : ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable(name="id") Long id, @RequestBody Product product) {
        product.setId(id);
        Product res = this.productService.updateProduct(product);
        if(null == product) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Product> delete(@PathVariable(name="id") Long id) {
        Product res = this.productService.deleteProduct(id);
        return ResponseEntity.ok(res);
    }

    @PatchMapping(value="/{id}/{stock}")
    public ResponseEntity<Product> updateStock(@PathVariable(name="id") Long id, @PathVariable(name="stock") Double stock) {
        Product res = this.productService.updateStock(id, stock);
        return (res == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(res);
    }


    private String formatMessage(BindingResult result) {
        List<Map<String, String>> list = result.getFieldErrors().stream().map(err -> {
            Map<String, String> error = new HashMap<>();
            error.put(err.getField(), err.getDefaultMessage());
            return error;
        }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder().code("01").messages(list).build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return jsonString;


    }

}
