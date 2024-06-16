package com.example.finanzasgrupo5backend.Credito1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumoCredito1Response {

    private Long id;
    private String producto;
    private Double precio;
    private String fechaInicial;
    private String fechaFinal;
    private Double interes;
    private Double montoConsumo;

    private Long credito1;
}
