package com.example.finanzasgrupo5backend.Validations.Credito2;

import com.example.finanzasgrupo5backend.Credito2.Model.Credito.Credito2Request;
import com.example.finanzasgrupo5backend.Shared.exception.ValidationException;

public class Credito2Validation {

    public static void ValidateCredito2(Credito2Request credito2Request){

        if(credito2Request.getTasa() == null || credito2Request.getTasa().describeConstable().isEmpty()){
            throw new ValidationException("El credito debe tener una tasa"); //error 400
        }



    }
}
