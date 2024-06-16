package com.example.finanzasgrupo5backend.Credito1.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoraCredito1Request {

    private String TEPm;
    private Double tasa;
    private Long dias_atraso;


    //private Credito1 credito1;
}
