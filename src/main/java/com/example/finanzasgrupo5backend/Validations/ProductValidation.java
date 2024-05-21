package com.example.finanzasgrupo5backend.Validations;

import com.example.finanzasgrupo5backend.Products.Model.ProductRequest;
import com.example.finanzasgrupo5backend.Shared.exception.ValidationException;

import java.time.LocalDate;

public class ProductValidation {

    public static void ValidateProduct(ProductRequest productRequest){

        if(productRequest.getName() == null || productRequest.getName().isEmpty()){
            throw new ValidationException("El producto debe tener un nombre"); //error 400
        }

    }
}
