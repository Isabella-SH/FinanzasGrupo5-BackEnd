package com.example.finanzasgrupo5backend.Credito2.Model.Credito;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credito2Request {

    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private Double credito_limit;
    private String TEP;
    private Double tasa;
    private Long cuotas;
    private Long dias_plazo_gracias;
    //private Client client;

}
