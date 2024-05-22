package com.example.finanzasgrupo5backend.Profile.Model;

import com.example.finanzasgrupo5backend.Products.Model.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
