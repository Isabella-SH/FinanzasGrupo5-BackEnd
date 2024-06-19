package com.example.finanzasgrupo5backend.Credito2.Service;

import com.example.finanzasgrupo5backend.Credito2.Model.Mora.MoraCredito2Request;
import com.example.finanzasgrupo5backend.Credito2.Model.Mora.MoraCredito2Response;
import com.example.finanzasgrupo5backend.Credito2.Repository.IConsumoCredito2Repository;
import com.example.finanzasgrupo5backend.Credito2.Repository.ICredito2Repository;
import com.example.finanzasgrupo5backend.Credito2.Repository.IMoraCredito2Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("moraCredito2ServiceImpl")
public class MoraCredito2ServiceImpl implements IMoraCredito2Service {

    private final IMoraCredito2Repository moraCredito2Repository;
    private final ICredito2Repository credito2Repository;
    private final IConsumoCredito2Repository consumoCredito2Repository;
    private final ModelMapper modelMapper;

    public MoraCredito2ServiceImpl(IMoraCredito2Repository moraCredito2Repository, ICredito2Repository credito2Repository, IConsumoCredito2Repository consumoCredito2Repository, ModelMapper modelMapper) {
        this.moraCredito2Repository = moraCredito2Repository;
        this.credito2Repository = credito2Repository;
        this.consumoCredito2Repository = consumoCredito2Repository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<MoraCredito2Response> getAllMoraCredito2() {
        return null;
    }

    @Override
    public List<MoraCredito2Response> getMoraCredito2ByCreditoId(Long credito2) {
        return null;
    }

    @Override
    public MoraCredito2Response createMoraCredito2(MoraCredito2Request moracredito2Request, Long credito2Id) {
        return null;
    }

    @Override
    public MoraCredito2Response updateMoraCredito2(Long id, String TEPm, Double tasa, Double numero_cuota, Double dias_atraso, Double total_moras, Long credito2) {
        return null;
    }

    @Override
    public MoraCredito2Response deleteMoraCredito2(Long id) {
        return null;
    }
}
