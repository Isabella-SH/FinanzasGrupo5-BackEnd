package com.example.finanzasgrupo5backend.Validations.Credito1;

import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1Request;
import com.example.finanzasgrupo5backend.Credito1.Model.Credito1;
import com.example.finanzasgrupo5backend.Products.Model.Product;
import com.example.finanzasgrupo5backend.Shared.exception.ValidationException;

public class ConsumoCredito1Validation {

    public static void validateConsumoCredito1(ConsumoCredito1Request consumoCredito1, Credito1 credito, Product product){

        if (!credito.getClient().getStore_id().getId().equals(product.getStore().getId())) {
            throw new ValidationException("El producto no pertenece a la tienda del cliente"); //error 400
        }

        if(consumoCredito1.getFechaInicial() == null ){
            throw new ValidationException("El consumo debe tener una fecha inicial"); //error 400
        }

    }
}
