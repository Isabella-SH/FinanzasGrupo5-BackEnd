package com.example.finanzasgrupo5backend.Credito2.Model.Pago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoCredito2Response {
    private Long id;
    private Double renta;
    private Double total_moras;
    private Double total_monto_consumos;
    private Double monto_a_pagar;
    private Long cuota;
    private String estado;
}
