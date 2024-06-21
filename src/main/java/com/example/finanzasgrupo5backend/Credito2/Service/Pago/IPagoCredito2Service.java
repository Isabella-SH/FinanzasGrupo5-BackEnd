package com.example.finanzasgrupo5backend.Credito2.Service.Pago;

import com.example.finanzasgrupo5backend.Credito2.Model.Pago.PagoCredito2;
import com.example.finanzasgrupo5backend.Credito2.Model.Pago.PagoCredito2Response;

import java.util.List;

public interface IPagoCredito2Service {
    public abstract void createPagoCredito2(Double renta, Double total_moras, Long cuota, String estado, Long id_credito2);

    public abstract void updatePagoCredito2(Long id, String estado);

    public abstract List<String[]> getPagoCredito2ByCredito2(Long creditoId);
    public abstract Double sumTotalProductosPago(Long creditoId, Long pago);

}
