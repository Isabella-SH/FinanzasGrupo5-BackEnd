package com.example.finanzasgrupo5backend.Credito2.Service.Consumo;

import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2;
import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2Request;
import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2Response;
import com.example.finanzasgrupo5backend.Credito2.Repository.Consumo.IConsumoCredito2Repository;
import com.example.finanzasgrupo5backend.Credito2.Repository.ICredito2Repository;
import com.example.finanzasgrupo5backend.Shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.lang.Math.round;

@Service
@Qualifier("consumoCredito2ServiceImpl")
public class ConsumoCredito2ServiceImpl implements IConsumoCredito2Service {
    private final IConsumoCredito2Repository consumoCredito2Repository;
    private final ICredito2Repository credito2Repository;
    private final ModelMapper modelMapper;

    public ConsumoCredito2ServiceImpl(IConsumoCredito2Repository consumoCredito2Repository, ICredito2Repository credito2Repository, ModelMapper modelMapper) {
        this.consumoCredito2Repository = consumoCredito2Repository;
        this.credito2Repository = credito2Repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ConsumoCredito2Response createConsumoCredito2(ConsumoCredito2Request consumoCredito2Request, Long creditoId) {
        var existingCredito2 = credito2Repository.findById(creditoId)
            .orElseThrow(() -> new ResourceNotFoundException("No se encontró un credito2 con ID: " + creditoId));

        var newConsumo = modelMapper.map(consumoCredito2Request, ConsumoCredito2.class);
        LocalDate fechaInicialCredito = existingCredito2.getFechaInicial();
        LocalDate fechaFinalCredito = existingCredito2.getFechaFinal();
        Long totalDaysCredito = (long) fechaInicialCredito.until(fechaFinalCredito).getDays();
        Long cuotas = existingCredito2.getCuotas();

        //encontrar en que cuota se encuentra la fecha incial del consumo dentro de las cuotas divididas por el total de dias del credito
        Long num_cuota =  (long) Math.ceil( (double) fechaInicialCredito.until(newConsumo.getFechaInicial()).getDays()/ totalDaysCredito);

        newConsumo.setCredito2(existingCredito2);
        newConsumo.setCuota(num_cuota);

        var savedConsumo = consumoCredito2Repository.save(newConsumo);
        var response = modelMapper.map(savedConsumo, ConsumoCredito2Response.class);

        return response;
    }

    @Override
    public ConsumoCredito2Response updateConsumoCredito2() {
        return null;
    }

    @Override
    public ConsumoCredito2Response deleteConsumoCredito2(Long id) {
        // Buscar el consumo
        var consumo = consumoCredito2Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un consumo con el id: " + id));

        // Eliminar el producto
        consumoCredito2Repository.delete(consumo);

        // Mapeo de la respuesta para confirmar eliminación
        var response = modelMapper.map(consumo, ConsumoCredito2Response.class);

        return response;
    }
}
