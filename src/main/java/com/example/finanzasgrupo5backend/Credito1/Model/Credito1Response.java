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
    private Boolean TEP;
    private Boolean TNP;
    private Long tasa;
    private Long perio_capitalizacion;

    //private Client client;
}
