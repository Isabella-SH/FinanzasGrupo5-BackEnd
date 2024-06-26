package com.example.finanzasgrupo5backend.Credito1.Service;


import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1;
import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1Request;
import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1Response;
import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1ResponseHTTP;

import java.time.LocalDate;
import java.util.List;

public interface IConsumoCredito1Service {

    public abstract List<ConsumoCredito1ResponseHTTP> getAllConsumosCredito1();

    List<ConsumoCredito1ResponseHTTP>  getConsumosByCredito1Id(Long creditoId);

    List<ConsumoCredito1ResponseHTTP>  getConsumosByCreditoFechaInicioAndFechaFin(Long creditoId, LocalDate fechaInicial, LocalDate fechaFinal);

    public abstract ConsumoCredito1ResponseHTTP createConsumo(ConsumoCredito1Request consumo, Long creditoId, Long productoId);

    public abstract ConsumoCredito1Response updateConsumo(Long id, String product, Long price, Long creditoId);

    public abstract ConsumoCredito1Response deleteConsumo(Long id);

    public abstract Double sumTotalConsumoByCreditoId(Long creditoId);
    public abstract List<String[]> listConsumosByCredito(Long creditoId);
}
