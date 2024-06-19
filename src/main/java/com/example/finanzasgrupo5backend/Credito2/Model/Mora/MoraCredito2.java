package com.example.finanzasgrupo5backend.Credito2.Model.Mora;

import com.example.finanzasgrupo5backend.Credito1.Model.Credito1;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "moraCredito2")
public class MoraCredito2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TEPm",nullable = false)
    private String TEPm;  // QUINCENAL, TRIMESTRAL,...

    @Column(name = "tasa", nullable = false)
    private Double tasa;

    @Column(name = "numero_cuota", nullable = false)
    private Double numero_cuota;

    @Column(name = "dias_atraso", nullable = false)
    private Double dias_atraso;

    @Column(name = "total_moras", nullable = false)
    private Double total_moras; //ESTO SALE DE LAS FORMULAS

    //muchas moras pertenecen a un credito
    @ManyToOne
    @JoinColumn(name = "credito1_id", nullable = false)
    private Credito1 credito1;

}
