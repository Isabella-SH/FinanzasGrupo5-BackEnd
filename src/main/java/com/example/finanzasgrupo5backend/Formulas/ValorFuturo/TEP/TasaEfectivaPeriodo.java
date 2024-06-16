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

    public static Double calcularInteresMoratorio(String tep, Double tasa, Double valorNominal, long diasAtraso){

        //Im= valor nominal [(1+tep)^(diastrasladar/diastep)  -1]
        Double ndp= numeroDiasPeriodo(tep);
        Double interes= tasa/100;

        Double interesMoratorio = (Double) (valorNominal * (Math.pow((1 + interes), (diasAtraso / ndp))-1));

        return interesMoratorio;
    }

    public static Double interes(Double tasa, Double precio){
        return precio * (tasa/100);
    }

}
