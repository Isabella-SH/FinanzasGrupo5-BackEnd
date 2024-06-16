package com.example.finanzasgrupo5backend.Credito1.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoCredito1Response {


    private Long id;
    private Double total_moras;
    private Double total_monto_consumos;
    private Double monto_a_pagar;



}
