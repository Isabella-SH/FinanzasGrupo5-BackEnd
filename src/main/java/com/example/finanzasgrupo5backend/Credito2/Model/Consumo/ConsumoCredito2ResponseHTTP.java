package com.example.finanzasgrupo5backend.Credito2.Model.Consumo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumoCredito2ResponseHTTP {
    private Long id;

    private String fechaInicial;

    private String fechaFinal;

    private Long cuota;

}
