package com.example.finanzasgrupo5backend.Credito2.Service.Pago;

public interface IPagoCredito2Service {
    public abstract void createPagoCredito2(Double renta, Double total_moras, Long cuota, String estado, Long id_credito2);

    public abstract void updatePagoCredito2(Long id, Double total_moras,Long cuota, String estado, Long id_credito2);
}
