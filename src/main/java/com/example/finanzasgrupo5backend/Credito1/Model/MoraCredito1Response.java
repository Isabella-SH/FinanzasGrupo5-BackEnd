package com.example.finanzasgrupo5backend.Credito1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoraCredito1Response {

    private Long id;
    private String TEPm;
    private Double tasa;
    private Long dias_atraso;
    private Double total_moras;

    //private Credito1 credito1;
}
