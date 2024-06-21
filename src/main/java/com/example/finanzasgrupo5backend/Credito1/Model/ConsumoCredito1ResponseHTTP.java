package com.example.finanzasgrupo5backend.Credito1.Model;

import com.example.finanzasgrupo5backend.Products.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumoCredito1ResponseHTTP {
    private Long id;

    private String fechaInicial;

    private String fechaFinal;

    private Double interes;

    private Double montoConsumo;

}
