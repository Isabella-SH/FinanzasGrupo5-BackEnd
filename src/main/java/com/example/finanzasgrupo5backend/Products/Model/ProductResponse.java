package com.example.finanzasgrupo5backend.Products.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

//lo que devuelve
public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
}
