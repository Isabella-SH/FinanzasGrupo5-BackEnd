package com.example.finanzasgrupo5backend.Formulas.ValorFuturo.TNP;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TasaNominalPeriodo {

    //parametros de entrada
    //fechas ->inicial,final
    //ten
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

    public static long numeroDiasPeriodo(String ten) {

        if (ten.equals("TNM")) {            return 30;        }
        if (ten.equals("TNQ")) {            return 15;        }
        if (ten.equals("TNS")) {            return 180;        }
        if (ten.equals("TNA")) {            return 360;        }
        if (ten.equals("TND")) {            return 1;        }
        if (ten.equals("TNC")) {            return 120;        }
        if (ten.equals("TNT")) {            return 90;        }
        if (ten.equals("TNB")) {            return 60;        }
        return 0;
    }

    public static long numeroDiasPeriodoCapitalizacion(String ten) {

        if (ten.equals("diario")) {             return 1;        }
        if (ten.equals("quincenal")) {            return 15;        }
        if (ten.equals("mensual")) {            return 30;        }
        if (ten.equals("bimestral")) {            return 60;        }
        if (ten.equals("trimestral")) {            return 90;        }
        if (ten.equals("cuatrimestral")) {            return 120;        }
        if (ten.equals("semestral")) {            return 180;        }
        if (ten.equals("anual")) {            return 360;        }
        return 0;
    }

    public static long tiempo(LocalDate fechaI, LocalDate fechaF) {
        return ChronoUnit.DAYS.between(fechaI, fechaF);
    }

    public static long montoPagarConsumo(LocalDate fechaI, LocalDate fechaF, String ten, long tasa, long precio, String periodoCapitalizacion) {

        //         TEP                  c.?              t=
        //          ? en P=m                     ? en t =n
        // s = c (1 + ten/m) ^ n

        long diasP = numeroDiasPeriodo(ten);
        long diasPC = numeroDiasPeriodoCapitalizacion(periodoCapitalizacion);

        long interesPorcentaje = tasa / 100;
        long t = tiempo(fechaI, fechaF);
        long m = diasP / diasPC;
        long n = t / diasPC;
        long interes = interesPorcentaje / m;

        long s = (long) (precio * Math.pow((1 + interes), n));

        return s;
    }

    public static long calcularInteresMoratorio(String tep, long tasa, long valorNominal, long diasAtraso) {

        //Im= valor nominal [(1+tep)^(diastrasladar/diastep)  -1]

        long ndp = numeroDiasPeriodo(tep);
        long interes = tasa / 100;

        long interesMoratorio = (long) (valorNominal * (Math.pow((1 + interes), (diasAtraso / ndp)) - 1));

        return interesMoratorio;
    }

    public static long interes(long tasa, long precio){
        return precio * (tasa/100);
    }
}
