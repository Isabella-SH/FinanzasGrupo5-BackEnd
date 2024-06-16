package com.example.finanzasgrupo5backend.Credito1.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoCredito1Response {


    private Long id;
    private Long total_moras;
    private Long total_monto_consumos;
    private Long monto_a_pagar;
    private Boolean pagado;

    //private Credito1 credito1;
    //private MoraCredito1 moraCredito1;
}
