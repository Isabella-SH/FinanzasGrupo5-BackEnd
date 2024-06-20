package com.example.finanzasgrupo5backend.Credito2.Model.Pago;

import com.example.finanzasgrupo5backend.Credito2.Model.Credito.Credito2;
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
@Table(name = "pagoCredito2")
public class PagoCredito2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "renta", nullable = false)
    private Double renta;

    @Column(name = "total_moras")
    private Double total_moras;

    @Column(name = "monto_a_pagar", nullable = false)
    private Double monto_a_pagar;

    @Column(name = "cuota", nullable = false)
    private Long cuota;

    @Column(name = "estado", nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "credito2_id", nullable = false)
    private Credito2 credito2;




}
