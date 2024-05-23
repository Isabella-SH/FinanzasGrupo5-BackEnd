package com.example.finanzasgrupo5backend.Profile.Model;

import com.example.finanzasgrupo5backend.Products.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {

    private Long id;
    private String name;
    List<Product> products;
}
