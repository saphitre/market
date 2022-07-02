package com.example.market.service.convert;

import com.example.market.model.dto.ProductDto;
import com.example.market.model.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConvert {
    public ProductDto entityToDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .build();
    }

    public Product dtoToEntity(ProductDto productDto){
        return Product.builder()
                .name(productDto.getName())
                .quantity(productDto.getQuantity())
                .build();
    }
}
