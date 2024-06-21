package com.example.finanzasgrupo5backend.Validations.Credito2;

import com.example.finanzasgrupo5backend.Credito2.Model.Mora.MoraCredito2Request;
import com.example.finanzasgrupo5backend.Credito2.Repository.Mora.IMoraCredito2Repository;
import com.example.finanzasgrupo5backend.Shared.exception.ValidationException;

public class MoraCreditos2Validation {

    public static void ValidateMoraCredito2(MoraCredito2Request moraCredito2Request){

        if(moraCredito2Request.getTasa()== null || moraCredito2Request.getTasa().describeConstable().isEmpty()){
            throw new ValidationException("La mora debe tener una tasa"); //error 400
        }
        if(moraCredito2Request.getDias_atraso()== null || moraCredito2Request.getDias_atraso().describeConstable().isEmpty()){
            throw new ValidationException("La mora debe tener la cantidad de dias de atraso"); //error 400
        }
        if(moraCredito2Request.getNumero_cuota()== null || moraCredito2Request.getNumero_cuota().describeConstable().isEmpty()){
            throw new ValidationException("La mora debe estar especificada a que numero de cuota pertenece"); //error 400
        }
    }

    public static void ValidateNumeroCuotaCorrecto(MoraCredito2Request moraCredito2Request, double numero_cuotas, long credito2Id, IMoraCredito2Repository moraCredito2Repository) {

        if(moraCredito2Request.getNumero_cuota()>numero_cuotas || moraCredito2Request.getNumero_cuota()<=0){
            throw new ValidationException("La cuota a la que pertenece la mora no es valida"); //error 400
        }

        // Número de cuota de la mora a validar
        double numeroCuotaMora = moraCredito2Request.getNumero_cuota();

        // Buscar mora existente con el mismo crédito ID y número de cuota
        var existingMora = moraCredito2Repository.findMoraByCredito2Id(credito2Id)
                .stream()
                .filter(mora -> mora.getNumero_cuota() == numeroCuotaMora)
                .findFirst();

        // Si se encuentra una mora con el mismo crédito ID y número de cuota, lanzar excepción
        if (existingMora.isPresent()) {
            throw new ValidationException("Ya existe una mora para la cuota: " + numeroCuotaMora);
        }

    }

}
