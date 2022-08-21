package com.example.market.service;

import com.example.market.model.entity.Product;
import com.example.market.repository.ProductRepository;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> getById(Long id){
        return productRepository.findById(id);
    }

    public HttpStatus deleteProductById(Long id){ productRepository.deleteById(id); return HttpStatus.OK;}

    public Optional<Product> findByName(String name){ return productRepository.findByName(name);}
}
