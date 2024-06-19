package com.example.finanzasgrupo5backend.Credito2.Model.Credito;


import com.example.finanzasgrupo5backend.Profile.Clients.Model.Client;
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
@Table(name = "creditos2")
public class Credito2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaInicial",nullable = false)
    private LocalDate fechaInicial;

    @Column(name = "fechaFinal",nullable = false)
    private LocalDate fechaFinal;

    @Column(name = "TEoN",nullable = false)
    private String TEoN;  //E -> EFECTIVA  N->NOMIMAL

    @Column(name = "TEP")
    private String TEP; //mensual, trimestral ...

    @Column(name = "TNP")
    private String TNP; //mensual, trimestral . ...

    @Column(name = "tasa", nullable = false)
    private Double tasa;

    @Column(name = "cuotas", nullable = false)
    private Long cuotas;

    @Column(name = "renta", nullable = false)
    private Double renta;

    @Column(name = "dias_plazo_gracias", nullable = false)
    private Long dias_plazo_gracias;

    //muchos creditos pertenecen a un cliente
    @ManyToOne
    @JoinColumn(name = "client", nullable = false)
    private Client client;


}
