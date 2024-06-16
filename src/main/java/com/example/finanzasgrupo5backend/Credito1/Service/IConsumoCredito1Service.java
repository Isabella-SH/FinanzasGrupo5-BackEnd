package com.example.finanzasgrupo5backend.Credito1.Service;


import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1;
import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1Request;
import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1Response;

import java.time.LocalDate;
import java.util.List;

public interface IConsumoCredito1Service {

    public abstract List<ConsumoCredito1Response> getAllConsumosCredito1();

    List<ConsumoCredito1Response>  getConsumosByCredito1Id(Long creditoId);

    List<ConsumoCredito1Response>  getConsumosByCreditoFechaInicioAndFechaFin(Long creditoId, LocalDate fechaInicial, LocalDate fechaFinal);

    public abstract ConsumoCredito1Response createConsumo(ConsumoCredito1Request consumo, Long creditoId);

    public abstract ConsumoCredito1Response updateConsumo(Long id, String product, Long price, Long creditoId);

    public abstract ConsumoCredito1Response deleteConsumo(Long id);

    public abstract Long sumTotalConsumoByCreditoId(Long creditoId);
}
