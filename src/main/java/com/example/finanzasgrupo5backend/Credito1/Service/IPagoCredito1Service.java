package com.example.finanzasgrupo5backend.Credito1.Service;

import com.example.finanzasgrupo5backend.Credito1.Model.*;

import java.util.List;

public interface IPagoCredito1Service {


    public abstract List<PagoCredito1Response> getAllPagoCredito1();

    List<PagoCredito1Response>  getPagoCredito1ByCreditoId(Long credito1);

    //falta pago by mora

    PagoCredito1Response createPagoCredito1(IMoraCredito1Service moraCredito1Service, PagoCredito1Request pagoCredito1Request, Long creditoId);

    public abstract PagoCredito1Response updatePagoCredito1(Long id, Long total_moras, Long total_monto_consumos, Long monto_a_pagar, Boolean pagado, Long credito1, Long moracredito1);

    public abstract PagoCredito1Response deletePagoCredito1(Long id);


}
