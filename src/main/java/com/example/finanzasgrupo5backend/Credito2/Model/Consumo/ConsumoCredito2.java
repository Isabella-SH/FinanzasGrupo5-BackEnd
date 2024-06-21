package com.example.finanzasgrupo5backend.Credito2.Model.Consumo;

import com.example.finanzasgrupo5backend.Credito2.Model.Credito.Credito2;
import com.example.finanzasgrupo5backend.Products.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consumoCredito2")
public class ConsumoCredito2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto", nullable = false)
    private Product producto;

    @Column(name = "fechaInicial",nullable = false)
    private LocalDate fechaInicial;

    @Column(name = "fechaFinal",nullable = false)
    private LocalDate fechaFinal;

    @Column(name = "cuota", nullable = false)
    private Long cuota;

    @ManyToOne
    @JoinColumn(name = "credito2", nullable = false)
    private Credito2 credito2;

}
