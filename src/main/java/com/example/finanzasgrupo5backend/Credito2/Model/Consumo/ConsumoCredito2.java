package com.example.finanzasgrupo5backend.Credito2.Model.Consumo;

import com.example.finanzasgrupo5backend.Credito2.Model.Credito.Credito2;
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

    @Column(name = "producto", nullable = false)
    private String producto;

    @Column(name = "fechaInicial",nullable = false)
    private LocalDate fechaInicial;

    @Column(name = "fechaFinal",nullable = false)
    private LocalDate fechaFinal;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "cuota", nullable = false)
    private Long cuota;

    @ManyToOne
    @JoinColumn(name = "credito2", nullable = false)
    private Credito2 credito2;

}
