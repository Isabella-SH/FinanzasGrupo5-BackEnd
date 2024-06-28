package com.example.finanzasgrupo5backend.Formulas.ValorFuturo.TNP;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TasaNominalPeriodo {

                                    //parametros de entrada
    //fechas ->inicial,final
    //tnp
    //tasa interes
    //precio
    //periodo capitalizacion -> m
    //perido que paso        -> n (dias)

                                      //variables
    //s-> stock
    //c ->capital
    //ndt -> numero de dias a trasladar
    //ndp -> numero de dias del periodo

    /////////////////////////////////////////////////////////////////

    public static Double numeroDiasPeriodo(String tnp) {

        if (tnp.equals("TNM")) {            return 30D;        }
        if (tnp.equals("TNQ")) {            return 15D;        }
        if (tnp.equals("TNS")) {            return 180D;        }
        if (tnp.equals("TNA")) {            return 360D;        }
        if (tnp.equals("TND")) {            return 1D;        }
        if (tnp.equals("TNC")) {            return 120D;        }
        if (tnp.equals("TNT")) {            return 90D;        }
        if (tnp.equals("TNB")) {            return 60D;        }
        return 0D;
    }

    public static Double numeroDiasPeriodoMora(String tep) {

        if(tep.equals("TEM")) { return 30D; }
        if(tep.equals("TEQ")) { return 15D; }
        if(tep.equals("TES")) { return 180D; }
        if(tep.equals("TEA")) { return 360D; }
        if(tep.equals("TED")) { return 1D; }
        if(tep.equals("TEC")) { return 120D; }
        if(tep.equals("TET")) { return 90D; }
        return 0D;
    }

    public static Double numeroDiasPeriodoCapitalizacion(String periodo_capi) {

        if (periodo_capi.equals("diario")) {            return 1D;        }
        if (periodo_capi.equals("quincenal")) {            return 15D;        }
        if (periodo_capi.equals("mensual")) {            return 30D;        }
        if (periodo_capi.equals("bimestral")) {            return 60D;        }
        if (periodo_capi.equals("trimestral")) {            return 90D;        }
        if (periodo_capi.equals("cuatrimestral")) {            return 120D;        }
        if (periodo_capi.equals("semestral")) {            return 180D;        }
        if (periodo_capi.equals("anual")) {            return 360D;        }
        return 0D;
    }

    public static long tiempo(LocalDate fechaI, LocalDate fechaF) {
        return ChronoUnit.DAYS.between(fechaI, fechaF);
    }

    public static Double montoPagarConsumo(LocalDate fechaI, LocalDate fechaF, String tnp, Double tasa, Double precio, String periodoCapitalizacion) {

        //         TNP                  c.?              t=
        //          ? en P=m                     ? en t =n
        // s = c (1 + ten/m) ^ n

        double diasP = numeroDiasPeriodo(tnp);
        double diasPC = numeroDiasPeriodoCapitalizacion(periodoCapitalizacion);

        double interesPorcentaje = tasa / 100;
        double t = tiempo(fechaI, fechaF);
        double m = diasP / diasPC;
        double n = t / diasPC;
        double interes = interesPorcentaje / m;

        Double s = (Double) (precio * Math.pow((1 + interes), n));

        return s;
    }

    public static Double calcularInteresCompensatorio(String tnp_credito, String periodo_capi, Double tasa_credito, Double valorNominal, Double diasAtraso) {

        //         TNP                  c.?              t= dias_atraso
        //          ? en P = m                     ? en t = n

        //Ic= valor nominal [(1+tnp/m)^ n  -1]

        double diasP = numeroDiasPeriodo(tnp_credito);
        double diasPC = numeroDiasPeriodoCapitalizacion(periodo_capi);
        double interes = tasa_credito / 100;

        double m = diasP / diasPC;
        double n= diasAtraso/diasPC;

        double interesCompensatorio = (Double) (valorNominal * (Math.pow((1 + (interes/m)), n) - 1));

        return interesCompensatorio;
    }

    public static Double calcularInteresMoratorio(String tep_mora, Double tasa_mora, Double valorNominal, Double diasAtraso) {

        //Im= valor nominal [(1+tep)^(diastrasladar/diastep)  -1]

        double ndp = numeroDiasPeriodoMora(tep_mora);
        double interes = tasa_mora / 100;

        Double interesMoratorio = (Double) (valorNominal * (Math.pow((1 + interes), (diasAtraso / ndp)) - 1));

        return interesMoratorio;
    }

    public static Double interes(Double montoConsumo, Double precio){
        return montoConsumo - precio;
    }

}
