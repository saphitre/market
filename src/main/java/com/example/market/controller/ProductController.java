package com.example.market.controller;

import com.example.market.exception.DuplicateProductException;
import com.example.market.exception.ProductNotFoundException;
import com.example.market.model.entity.Product;
import com.example.market.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ProductController {

    private static final Logger logger= LogManager.getLogger(ProductController.class);
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public static final String ADD_PRODUCT = "/save";
    public static final String DELETE_PRODUCT = "/deleteById/{product_id}";



    @GetMapping("/")
    public ResponseEntity getProductList(){
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping(ADD_PRODUCT)
    public ResponseEntity saveProduct(@RequestBody Product product) {
        try{
            logger.info("Product was created");
            return ResponseEntity.ok(productService.save(product));
        }
        catch (DataIntegrityViolationException e) {
            throw new DuplicateProductException();
        }
    }

    @GetMapping("/searchById/{product_id}")
    public ResponseEntity getProductById(@PathVariable("product_id") Long id){
        return ResponseEntity.ok(productService.getById(id));
    }

    @DeleteMapping(DELETE_PRODUCT)
    public ResponseEntity deleteProductById(@PathVariable("product_id") Long id) {
        try {
            return ResponseEntity.ok(productService.deleteProductById(id));
        } catch (EmptyResultDataAccessException e){
            throw new ProductNotFoundException();
        }

    }
}
