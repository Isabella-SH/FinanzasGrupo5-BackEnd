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
    private Long tasa;
    private Long dias_atraso;
    private Long total_moras;

    //private Credito1 credito1;
}
