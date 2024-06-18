package com.example.finanzasgrupo5backend.Formulas.Anualidades.SimpleVencida;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.example.finanzasgrupo5backend.Formulas.ValorFuturo.TEP.TasaEfectivaPeriodo.montoPagarConsumo;

public class AnualidadSimpleVencida {

    //parametros de entrada
    //fechas ->inicial,final
    //tep -> especificar
    //tasa interes
    //capital                   -> C
    //numero de cuotas          -> n
    //peridos de gracia (cuantas cuotas sera la gracia)         -> g


    //variables
    //i -> tasa con respecto a las fechas
    //ndt -> nuemro dias a trasladar
    //ndp -> numero de dias del periodo
    //s -> stock

    ////////////////////////////////////////////////////////////////////////////

    public static Double numeroDiasPorCuota(LocalDate fechaI, LocalDate fechaF, double n) {

        double dias_del_prestamo = ChronoUnit.DAYS.between(fechaI, fechaF);
        double dias_por_cuota = dias_del_prestamo / n;
        return dias_por_cuota;
    }

    public static Double numeroDiasPeriodo(String tep) {

        if (tep.equals("TEM")) {            return 30D;        }
        if (tep.equals("TEQ")) {            return 15D;        }
        if (tep.equals("TES")) {            return 180D;        }
        if (tep.equals("TEA")) {            return 360D;        }
        if (tep.equals("TED")) {            return 1D;        }
        if (tep.equals("TEC")) {            return 120D;        }
        if (tep.equals("TET")) {            return 90D;        }
        return 0D;
    }

    public static double conversionTasa(double dias_por_cuota, double dias_tep, double tasa) {

        //tasa efectiva a otra tasa efectiva
        // tep2 = (1+tep1)^ (#dias tep2 / #dias tep1)  - 1
        double sumando = 1 + tasa;
        double dividendo = dias_por_cuota / dias_tep;

        double tep2 = (Double) (Math.pow((sumando), (dividendo)) - 1);

        return tep2;
    }

    public static double calcularRenta(double capital, double tasa, String tep, LocalDate fechaI, LocalDate fechaF, double n) {

        //R= C ( i*(1+i)^n  /  (1+i)^n -1  )

        double dias_por_cuota = numeroDiasPorCuota(fechaI, fechaF, n);
        double dias_tep = numeroDiasPeriodo(tep);
        double numerador;
        double denominador;
        double interes = tasa / 100;

        if (dias_por_cuota == dias_tep) {
            //la tasa es la misma
            numerador = (Double) (interes * (Math.pow((1 + interes), (n))));
            denominador = (Double) ((Math.pow((1 + interes), (n)) - 1));
        } else {
            //hay que convertir la tasa correspondiente a los dias de la cuota
            double nueva_tasa = conversionTasa(dias_por_cuota, dias_tep, interes);
            numerador = (Double) (nueva_tasa * (Math.pow((1 + nueva_tasa), (n))));
            denominador = (Double) ((Math.pow((1 + nueva_tasa), (n)) - 1));
        }

        double renta = capital * (numerador / denominador);
        return renta;
    }

    //periodo de gracia es parte del rango de fechas
    public static double calcularRentaConPeridoDeGracia(double capital, double tasa, String tep, LocalDate fechaI, LocalDate fechaF, double n, double g) {

        //calcular el capital despues del perido de gracia con la tasa original
        //S = C (1+ i) ^ (ndt / ndp)
        //usar formula del archivo tasaEfectivaPeriodo
        double nuevo_capital = montoPagarConsumo(fechaI, fechaF, tep, tasa, capital);
        double nueva_cantidad_rentas = n - g; //-> n

        //R= C ( i*(1+i)^n  /  (1+i)^n -1  )
        double dias_por_cuota = numeroDiasPorCuota(fechaI, fechaF, n);
        double dias_tep = numeroDiasPeriodo(tep);
        double numerador;
        double denominador;
        double interes = tasa / 100;

        if (dias_por_cuota == dias_tep) {
            //la tasa es la misma
            numerador = (Double) (interes * (Math.pow((1 + interes), (nueva_cantidad_rentas))));
            denominador = (Double) ((Math.pow((1 + interes), (nueva_cantidad_rentas)) - 1));
        } else {
            //hay que convertir la tasa correspondiente a los dias de la cuota
            double nueva_tasa = conversionTasa(dias_por_cuota, dias_tep, interes);
            numerador = (Double) (nueva_tasa * (Math.pow((1 + nueva_tasa), (nueva_cantidad_rentas))));
            denominador = (Double) ((Math.pow((1 + nueva_tasa), (nueva_cantidad_rentas)) - 1));
        }

        double renta = nuevo_capital * (numerador / denominador);
        return renta;
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




}
