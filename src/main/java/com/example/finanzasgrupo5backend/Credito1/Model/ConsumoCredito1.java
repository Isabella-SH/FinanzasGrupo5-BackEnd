package com.example.finanzasgrupo5backend.Credito1.Model;

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
@Table(name = "consumoCredito1")
public class ConsumoCredito1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto", nullable = false)
    private String producto;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "fechaInicial",nullable = false)
    private LocalDate fechaInicial;

    @Column(name = "fechaFinal",nullable = false)
    private LocalDate fechaFinal;

    @Column(name = "interes", nullable = false)
    private Double interes;

    @Column(name = "monto_consumo", nullable = false)
    private Double montoConsumo;

    //muchos consumos pertenecen a un credito
    @ManyToOne
    @JoinColumn(name = "creditos1", nullable = false)
    private Credito1 credito1;

}
