package com.example.finanzasgrupo5backend.Validations.Credito1;

import com.example.finanzasgrupo5backend.Credito1.Model.MoraCredito1Request;

import com.example.finanzasgrupo5backend.Shared.exception.ValidationException;

public class MoraCreditos1Validation {

    public static void ValidateMoraCredito1(MoraCredito1Request moraCredito1Request){

        if(moraCredito1Request.getTasa()== null || moraCredito1Request.getTasa().describeConstable().isEmpty()){
            throw new ValidationException("La mora debe tener una tasa"); //error 400
        }

    }



}
