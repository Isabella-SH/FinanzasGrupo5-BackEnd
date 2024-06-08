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

    public static long numeroDiasPeriodo(String tep) {

        if(tep.equals("TEM")) { return 30; }
        if(tep.equals("TEQ")) { return 15; }
        if(tep.equals("TES")) { return 180; }
        if(tep.equals("TEA")) { return 360; }
        if(tep.equals("TED")) { return 1; }
        if(tep.equals("TEC")) { return 120; }
        if(tep.equals("TET")) { return 90; }
        return 0;
    }

    public static long numeroDiasTrasladar(LocalDate fechaI, LocalDate fechaF) {
        return ChronoUnit.DAYS.between(fechaI, fechaF);
    }

    public static long montoPagarConsumo(LocalDate fechaI, LocalDate fechaF, String tep, long tasa, long precio) {
        // s = c (1 + tep) ^ (ndt / ndp)
        long interes= tasa/100;
        long ndp = numeroDiasPeriodo(tep);
        long ndt = numeroDiasTrasladar(fechaI, fechaF);

        long s = (long) (precio * Math.pow((1 + interes), (ndt / ndp)));

        return s;
    }

    public static long calcularInteresMoratorio(String tep, long tasa, long valorNominal, long diasAtraso){

        //Im= valor nominal [(1+tep)^(diastrasladar/diastep)  -1]
        long ndp= numeroDiasPeriodo(tep);
        long interes= tasa/100;

        long interesMoratorio = (long) (valorNominal * (Math.pow((1 + interes), (diasAtraso / ndp))-1));

        return interesMoratorio;
    }

    public static long interes(long tasa, long precio){
        return precio * (tasa/100);
    }

}
