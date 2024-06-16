package com.example.finanzasgrupo5backend.Credito1.Service;


import com.example.finanzasgrupo5backend.Credito1.Model.*;


import java.util.List;

public interface IMoraCredito1Service {
    public abstract List<MoraCredito1Response> getAllMoraCredito1();

    List<MoraCredito1Response>  getMoraCredito1ByCreditoId(Long credito1);

    MoraCredito1Response createMoraCredito1(MoraCredito1Request moracredito1Request, Long creditoId);

    public abstract MoraCredito1Response updateMoraCredito1(Long id, String TEPm, Double tasa, Long dias_atraso, Double total_moras, Long credito1);

    public abstract MoraCredito1Response deleteMoraCredito1(Long id);



}
