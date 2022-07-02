package com.example.market.controller;

import com.example.market.model.entity.Product;
import com.example.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity getProductList(){
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity saveProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @PostMapping("/saveAndFlush")
    public void savingProduct(@RequestParam String name, @RequestParam Integer quantity) {
        productService.saveAndFlush(Product.builder().name(name).quantity(quantity).build());
    }

    @GetMapping("/product")
    public ResponseEntity getProductById(Long id){
        return ResponseEntity.ok(productService.getById(id));
    }
}
