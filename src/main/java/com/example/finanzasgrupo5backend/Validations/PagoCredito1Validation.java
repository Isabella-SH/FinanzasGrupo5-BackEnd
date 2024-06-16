package com.example.finanzasgrupo5backend.Validations;

import com.example.finanzasgrupo5backend.Credito1.Model.PagoCredito1Request;
import com.example.finanzasgrupo5backend.Shared.exception.ValidationException;

public class PagoCredito1Validation {


    public static void ValidatePagoCredito1(PagoCredito1Request pagoCredito1Request) throws ValidationException {

        if(pagoCredito1Request.getMonto_a_pagar()== null || pagoCredito1Request.getMonto_a_pagar().describeConstable().isEmpty()){
            throw new ValidationException("El pago debe tener un monto a pagar"); //error 400
        }

    }
}
