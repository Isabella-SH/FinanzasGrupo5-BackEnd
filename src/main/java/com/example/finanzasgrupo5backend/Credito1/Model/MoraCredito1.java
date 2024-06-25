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
@Table(name = "moraCredito1")
public class MoraCredito1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TEPm",nullable = false)
    private String TEPm;  // QUINCENAL, TRIMESTRAL,...

    @Column(name = "tasa", nullable = false)
    private Double tasa;

    @Column(name = "dias_atraso", nullable = false)
    private Double dias_atraso;

    @Column(name = "total_moras", nullable = false)
    private Double total_moras; //ESTO SALE DE LAS FORMULAS

    //muchos creditos pertenecen a un cliente
    @ManyToOne
    @JoinColumn(name = "credito1_id", nullable = false)
    private Credito1 credito1;


}
