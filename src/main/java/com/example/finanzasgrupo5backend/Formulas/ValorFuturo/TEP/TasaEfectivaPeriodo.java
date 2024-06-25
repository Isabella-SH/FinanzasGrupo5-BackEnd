package com.example.finanzasgrupo5backend.Formulas.ValorFuturo.TEP;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TasaEfectivaPeriodo {

                               //parametros de entrada
    //fechas ->inicial,final
    //tep
    //tasa interes
    //precio

                                 //variables
    //s-> stock
    //c ->capital
    //ndt -> numero de dias a trasladar
    //ndp -> numero de dias del periodo

    /////////////////////////////////////////////////////////////////

    public static Double numeroDiasPeriodo(String tep) {

        if(tep.equals("TEM")) { return 30D; }
        if(tep.equals("TEQ")) { return 15D; }
        if(tep.equals("TES")) { return 180D; }
        if(tep.equals("TEA")) { return 360D; }
        if(tep.equals("TED")) { return 1D; }
        if(tep.equals("TEC")) { return 120D; }
        if(tep.equals("TET")) { return 90D; }
        return 0D;
    }

    public static long numeroDiasTrasladar(LocalDate fechaI, LocalDate fechaF) {
        return ChronoUnit.DAYS.between(fechaI, fechaF);
    }

    public static Double montoPagarConsumo(LocalDate fechaI, LocalDate fechaF, String tep, Double tasa, Double precio) {
        // s = c (1 + tep) ^ (ndt / ndp)
        Double interes= tasa/100D;
        Double ndp = numeroDiasPeriodo(tep);
        long ndt = numeroDiasTrasladar(fechaI, fechaF);

        Double s = (Double) (precio * Math.pow((1 + interes), (ndt / ndp)));

        return s;
    }

    public static Double calcularInteresCompensatorio(String tep_credito, Double tasa_credito, Double valorNominal, long diasAtraso) {

        //Ic= valor nominal [(1+tep)^(dias trasladar/ dias periodo)  -1]
        Double ndp = numeroDiasPeriodo(tep_credito);
        Double interes = tasa_credito / 100;

        Double interesCompensatorio = (Double) (valorNominal * (Math.pow((1 + interes), (diasAtraso / ndp)) - 1));

        return interesCompensatorio;
    }

    public static Double calcularInteresMoratorio(String tep_mora, Double tasa_mora, Double valorNominal, long diasAtraso) {

        //Im= valor nominal [(1+tep)^(dias trasladar/ dias periodo)  -1]
        Double ndp = numeroDiasPeriodo(tep_mora);
        Double interes = tasa_mora / 100;

        Double interesMoratorio = (Double) (valorNominal * (Math.pow((1 + interes), (diasAtraso / ndp)) - 1));

        return interesMoratorio;
    }

    public static Double interes(Double montoConsumo, Double precio){
        return montoConsumo - precio;
    }
}
