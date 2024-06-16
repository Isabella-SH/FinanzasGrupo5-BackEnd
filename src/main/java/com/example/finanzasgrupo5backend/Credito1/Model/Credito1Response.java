package com.example.finanzasgrupo5backend.Credito1.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Credito1Response {

    private Long id;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private String TEoN;
    private String TEP;
    private String TNP;
    private Double tasa;
    private String perio_capitalizacion;

    //private Client client;
}
