package com.example.finanzasgrupo5backend.Credito1.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAnyAttribute;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "moraCredito1")
public class PagoCredito1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_moras", nullable = false)
    private Long total_moras;

    @Column(name = "total_monto_consumos", nullable = false)
    private Long total_monto_consumos;

    @Column(name = "monto_a_pagar", nullable = false)
    private Long monto_a_pagar;

    @Column(name = "pagado", nullable = true)
    private Boolean pagado;

    @ManyToOne
    @JoinColumn(name = "credito1_id", nullable = false)
    private Credito1 credito1;





}
