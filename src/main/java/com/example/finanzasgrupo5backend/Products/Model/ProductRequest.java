package com.example.finanzasgrupo5backend.Products.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

//lo que recibe
public class ProductRequest {

    private String name;
    private Double price;

    //private Long store_id;
}
