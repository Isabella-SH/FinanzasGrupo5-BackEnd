package com.example.finanzasgrupo5backend.Credito1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ConsumoCredito1Request {

    private String producto;
    private Long precio;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private Long diasTrasladar;
    private Long diasTasaPeriodo;
    private Long interes;
    private Long montoConsumo;

    //private Credito1 credito1;
}
