package com.example.market.service;

import com.example.market.model.entity.Product;
import com.example.market.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product saveAndFlush(Product product){
        return productRepository.saveAndFlush(product);
    }

    public Product getById(Long id){
        return productRepository.getById(id);
    }
}
