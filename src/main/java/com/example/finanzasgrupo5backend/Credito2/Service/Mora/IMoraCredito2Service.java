package com.example.finanzasgrupo5backend.Credito2.Service.Mora;

import com.example.finanzasgrupo5backend.Credito2.Model.Mora.MoraCredito2Request;
import com.example.finanzasgrupo5backend.Credito2.Model.Mora.MoraCredito2Response;

import java.util.List;

public interface IMoraCredito2Service {

    public abstract List<MoraCredito2Response> getAllMoraCredito2();

    List<MoraCredito2Response>  getMoraCredito2ByCreditoId(Long credito2);

    MoraCredito2Response createMoraCredito2(MoraCredito2Request moracredito2Request, Long credito2Id);

    public abstract MoraCredito2Response updateMoraCredito2(Long id, String TEPm, Double tasa, Double dias_atraso);

    public abstract MoraCredito2Response deleteMoraCredito2(Long id);

}
