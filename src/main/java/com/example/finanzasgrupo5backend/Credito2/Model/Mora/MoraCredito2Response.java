package com.example.finanzasgrupo5backend.Credito2.Model.Mora;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MoraCredito2Response {

    private Long id;
    private String TEPm;
    private Double tasa;
    private Double numero_cuota;
    private Double dias_atraso;
    private Double total_moras;
}
