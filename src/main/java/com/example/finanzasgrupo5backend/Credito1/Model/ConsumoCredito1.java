package com.example.finanzasgrupo5backend.Credito1.Model;

import com.example.finanzasgrupo5backend.Cronograma.ValorFuturo.Model.CronogramaValorFuturo;
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
    private Long precio;

    @Column(name = "fechaInicial",nullable = false)
    private LocalDate fechaInicial; //fecha de pago

    @Column(name = "fechaFinal",nullable = false)
    private LocalDate fechaFinal;

    @Column(name = "interes", nullable = false)
    private Long interes;

    @Column(name = "monto_consumo", nullable = false)
    private Long montoConsumo;

    //muchos consumos pertenecen a un credito
    @ManyToOne
    @JoinColumn(name = "id_creditos1", nullable = false)
    private Credito1 credito1;




}
