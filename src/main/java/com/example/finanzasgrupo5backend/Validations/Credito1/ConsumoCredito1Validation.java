package com.example.finanzasgrupo5backend.Validations.Credito1;

import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1Request;
import com.example.finanzasgrupo5backend.Shared.exception.ValidationException;

public class ConsumoCredito1Validation {

    public static void validateConsumoCredito1(ConsumoCredito1Request consumoCredito1){

        if(consumoCredito1.getProducto() == null || consumoCredito1.getProducto().isEmpty()){
            throw new ValidationException("El consumo debe tener un producto"); //error 400
        }

        if(consumoCredito1.getFechaInicial() == null ){
            throw new ValidationException("El consumo debe tener una fecha inicial"); //error 400
        }

        if(consumoCredito1.getFechaFinal() == null){
            throw new ValidationException("El consumo debe tener una afecha final"); //error 400
        }

        if(consumoCredito1.getPrecio() == null ){
            throw new ValidationException("El consumo debe tener un precio de producto"); //error 400
        }
    }
}
