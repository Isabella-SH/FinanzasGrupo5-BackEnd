package com.example.finanzasgrupo5backend.Credito2.Model.Credito;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Credito2Response {

    private Long id;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private String TEoN;
    private String TEP;
    private String TNP;
    private Double tasa;
    private Long cuotas;
    private Double renta;
    private Long dias_plazo_gracias;
    //private Client client;



}