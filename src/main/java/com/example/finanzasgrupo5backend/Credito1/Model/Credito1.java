package com.example.finanzasgrupo5backend.Credito1.Model;

import com.example.finanzasgrupo5backend.Clients.Model.Client;
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
@Table(name = "creditos1")
public class Credito1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaInicial",nullable = false)
    private LocalDate fechaInicial;  // yyyy-mm-dd

    @Column(name = "fechaFinal",nullable = false)
    private LocalDate fechaFinal;   // yyyy-mm-dd

    @Column(name = "TEoN",nullable = false)
    private String TEoN;  //E -> EFECTIVA  N->NOMIMAL

    @Column(name = "TEP")
    private String TEP; //TEM , TEA , ...  checkbox

    @Column(name = "TNP")
    private String TNP; //TNM , TNQ , ... checkbox

    @Column(name = "tasa", nullable = false)
    private Long tasa; //enviar solo numero  sin porcentaje

    @Column(name = "perio_capitalizacion")
    private String perio_capitalizacion; //quincenal , diaria ,.... , checkbox

    //muchos creditos pertenecen a un cliente
    @ManyToOne
    @JoinColumn(name = "client", nullable = false)
    private Client client;
}
