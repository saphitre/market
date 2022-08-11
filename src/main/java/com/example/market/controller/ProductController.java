package com.example.market.controller;

import com.example.market.exception.DuplicateProductException;
import com.example.market.exception.ProductNotFoundException;
import com.example.market.model.entity.Product;
import com.example.market.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ProductController", description = "Контроллер для управления продуктами(товарами)")
@RestController
@RequestMapping("/")
public class ProductController {

    private static final String ADD_PRODUCT = "/save";

    private static final String DELETE_PRODUCT = "/deleteById/{product_id}";

    private static final String GET_ALL_PRODUCTS="/products";

    private static final String SEARCH_PRODUCT_BY_ID="/searchById/{product_id}";

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Получение полного списка товаров",description = "Получение полного списка товаров из БД, для всех пользователей")
    @PreAuthorize("permitAll()")
    @GetMapping(GET_ALL_PRODUCTS)
    public ResponseEntity getProductList(){
        return ResponseEntity.ok(productService.getAll());
    }

    @Operation(summary = "Добавление товара", description = "Добавление нового товара(уникального), для пользователей с ролью ADMIN")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(ADD_PRODUCT)
    public ResponseEntity saveProduct(@RequestBody Product product) {
        try{
            return ResponseEntity.ok(productService.save(product));
        }
        catch (DataIntegrityViolationException e) {
            throw new DuplicateProductException();
        }
    }

    @Operation(summary = "Поиск продукта", description = "Поиск продукта по идентификационному номеру товара, для авторизированных пользователей")
    @PreAuthorize("isAuthenticated()")
    @GetMapping(SEARCH_PRODUCT_BY_ID)
    public ResponseEntity getProductById(@PathVariable("product_id") Long id){
        return ResponseEntity.ok(productService.getById(id));
    }

    @Operation(summary = "Удалить продукт", description = "Удаление продукта по идентификационному номеру, для пользователей с ролью ADMIN")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(DELETE_PRODUCT)
    public ResponseEntity deleteProductById(@Parameter(description = "Идентификационный номер продукта") @PathVariable("product_id") Long id) {
        try {
            return ResponseEntity.ok(productService.deleteProductById(id));
        } catch (EmptyResultDataAccessException e){
            throw new ProductNotFoundException();
        }

    }
}
