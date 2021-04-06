package com.example.firebase.springbootfirebasedemo.controller;

import com.example.firebase.springbootfirebasedemo.entity.Product;
import com.example.firebase.springbootfirebasedemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public String saveProduct(@RequestBody Product product) throws ExecutionException, InterruptedException {

        return productService.saveProduct(product);
    }

    @GetMapping("/products/{name}")
    public Product getProduct(@PathVariable String name) throws ExecutionException, InterruptedException {

        return productService.getProductDetailsByname(name);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() throws ExecutionException, InterruptedException {

        return productService.getProductDetails();
    }


    @PutMapping("/products")
    public String update(@RequestBody Product product) throws ExecutionException, InterruptedException {

        return productService.updateProduct(product);
    }


    @DeleteMapping("/products/{name}")
    public String deleteProduct(@PathVariable String name) throws ExecutionException, InterruptedException {

        return productService.deleteProduct(name);
    }
}
