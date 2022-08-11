package com.example.market.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@Schema(name = "Product", description = "Данные о товаре")
public class Product {
    @Schema(description = "Идентификатор товара (генерируется автоматически)")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Название товара")
    @Column(name = "name", unique = true)
    private String name;

    @Schema(description = "Количество товара")
    @Column(name = "quantity")
    private Integer quantity;
}
