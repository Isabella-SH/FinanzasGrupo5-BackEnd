package com.example.finanzasgrupo5backend.Profile.Store.Model;

import com.example.finanzasgrupo5backend.Products.Model.Product;
import com.example.finanzasgrupo5backend.Users.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequest {
    private Long id;
    private String name;


}
