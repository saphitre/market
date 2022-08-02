package com.example.market.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductDto {

//    @JsonProperty("id")
//    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("quantity")
    private Integer quantity;
}

