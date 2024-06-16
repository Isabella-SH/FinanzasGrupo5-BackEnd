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
    private Long tasa;

    @Column(name = "dias_atraso", nullable = false)
    private Long dias_atraso;

    @Column(name = "total_moras", nullable = false)
    private Long total_moras; //ESTO SALE DE LAS FORMULAS

    //muchos creditos pertenecen a un cliente
    @ManyToOne
    @JoinColumn(name = "credito1_id", nullable = false)
    private Credito1 credito1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTEPm() {
        return TEPm;
    }

    public void setTEPm(String TEPm) {
        this.TEPm = TEPm;
    }

    public Long getTasa() {
        return tasa;
    }

    public void setTasa(Long tasa) {
        this.tasa = tasa;
    }

    public Long getDias_atraso() {
        return dias_atraso;
    }

    public void setDias_atraso(Long dias_atraso) {
        this.dias_atraso = dias_atraso;
    }

    public Long getTotal_moras() {
        return total_moras;
    }

    public void setTotal_moras(Long total_moras) {
        this.total_moras = total_moras;
    }

    public Credito1 getCredito1() {
        return credito1;
    }

    public void setCredito1(Credito1 credito1) {
        this.credito1 = credito1;
    }
}
