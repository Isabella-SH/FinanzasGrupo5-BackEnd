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

    public static float numeroDiasPeriodo(String tep) {

        if(tep.equals("TEM")) { return 30; }
        if(tep.equals("TEQ")) { return 15; }
        if(tep.equals("TES")) { return 180; }
        if(tep.equals("TEA")) { return 360; }
        if(tep.equals("TED")) { return 1; }
        if(tep.equals("TEC")) { return 120; }
        if(tep.equals("TET")) { return 90; }
        return 0;
    }

    public static float numeroDiasTrasladar(LocalDate fechaI, LocalDate fechaF) {
        return ChronoUnit.DAYS.between(fechaI, fechaF);
    }

    public static float montoPagarConsumo(LocalDate fechaI, LocalDate fechaF, String tep, Float tasa, Float precio) {
        // s = c (1 + tep) ^ (ndt / ndp)
        float interes= tasa/100;
        float ndp = numeroDiasPeriodo(tep);
        float ndt = numeroDiasTrasladar(fechaI, fechaF);

        float s = (float) (precio * Math.pow((1 + interes), (ndt / ndp)));

        return s;
    }

    public static float calcularInteresMoratorio(String tep, float tasa, float valorNominal, float diasAtraso){

        //Im= valor nominal [(1+tep)^(diastrasladar/diastep)  -1]
        float ndp= numeroDiasPeriodo(tep);
        float interes= tasa/100;

        float interesMoratorio = (float) (valorNominal * (Math.pow((1 + interes), (diasAtraso / ndp))-1));

        return interesMoratorio;
    }

}
