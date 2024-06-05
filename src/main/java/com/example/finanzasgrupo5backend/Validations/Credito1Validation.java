package com.example.finanzasgrupo5backend.Validations;

import com.example.finanzasgrupo5backend.Credito1.Model.Credito1Request;
import com.example.finanzasgrupo5backend.Shared.exception.ValidationException;

public class Credito1Validation {

    public static void ValidateCredito1(Credito1Request credito1Request){

        if(credito1Request.getTasa() == null || credito1Request.getTasa().describeConstable().isEmpty()){
            throw new ValidationException("El credito debe tener una tasa"); //error 400
        }

    }
}
