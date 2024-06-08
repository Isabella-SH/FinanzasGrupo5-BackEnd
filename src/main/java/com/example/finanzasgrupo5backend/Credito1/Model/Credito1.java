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
    private LocalDate fechaInicial;

    @Column(name = "fechaFinal",nullable = false)
    private LocalDate fechaFinal;

    @Column(name = "TEoN",nullable = false)
    private String TEoN;

    @Column(name = "TEP")
    private String TEP;

    @Column(name = "TNP")
    private String TNP;

    @Column(name = "tasa", nullable = false)
    private Long tasa;

    @Column(name = "perio_capitalizacion")
    private String perio_capitalizacion;

    //muchos creditos pertenecen a un cliente
    @ManyToOne
    @JoinColumn(name = "client", nullable = false)
    private Client client;
}
