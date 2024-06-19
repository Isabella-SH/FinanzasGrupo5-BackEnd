package com.example.finanzasgrupo5backend.Credito2.Service.Consumo;

import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2;
import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2Request;
import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2Response;

public interface IConsumoCredito2Service {
    public abstract ConsumoCredito2Response createConsumoCredito2(ConsumoCredito2Request consumoCredito2Request, Long creditoId);
    public abstract ConsumoCredito2Response updateConsumoCredito2();
    public abstract ConsumoCredito2Response deleteConsumoCredito2(Long id);
}
