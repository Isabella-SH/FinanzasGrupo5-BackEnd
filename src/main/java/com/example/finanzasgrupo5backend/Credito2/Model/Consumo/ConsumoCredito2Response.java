package com.example.finanzasgrupo5backend.Credito2.Model.Consumo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumoCredito2Response {
    private Long id;
    private String productos;
    private Double precio;
    private String fechaInicial;
    private String fechaFinal;
    private Long cuotas;
    private Long creditos2;
}
