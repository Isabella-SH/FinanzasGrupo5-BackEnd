package com.example.finanzasgrupo5backend.Credito2.Service.Consumo;

import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2;
import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2Request;
import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2Response;
import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2ResponseHTTP;

import java.util.List;

public interface IConsumoCredito2Service {
    public abstract ConsumoCredito2ResponseHTTP createConsumoCredito2(ConsumoCredito2Request consumoCredito2Request, Long creditoId, Long productoId);
    public abstract ConsumoCredito2Response updateConsumoCredito2();
    public abstract ConsumoCredito2Response deleteConsumoCredito2(Long id);

    public abstract List<String[]> listConsumosByCredito(Long creditoId);
}
