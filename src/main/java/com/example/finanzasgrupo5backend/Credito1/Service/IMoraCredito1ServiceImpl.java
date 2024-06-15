package com.example.finanzasgrupo5backend.Credito1.Service;


import com.example.finanzasgrupo5backend.Credito1.Model.*;
import com.example.finanzasgrupo5backend.Credito1.Repository.IConsumoCredito1Repository;
import com.example.finanzasgrupo5backend.Credito1.Repository.ICredito1Repository;
import com.example.finanzasgrupo5backend.Credito1.Repository.IMoraCredito1Repository;
import com.example.finanzasgrupo5backend.Formulas.ValorFuturo.TEP.TasaEfectivaPeriodo;
import com.example.finanzasgrupo5backend.Shared.exception.ResourceNotFoundException;
import com.example.finanzasgrupo5backend.Validations.MoraCreditos1Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Qualifier("moraCredito1ServiceImpl")
public class IMoraCredito1ServiceImpl implements IMoraCredito1Service {
    private final IMoraCredito1Repository moraCredito1Repository;
    private final ICredito1Repository credito1Repository;
    private final IConsumoCredito1Repository consumoCredito1Repository;
    private final ModelMapper modelMapper;


    public IMoraCredito1ServiceImpl(IMoraCredito1Repository moraCredito1Repository, ICredito1Repository credito1Repository, IConsumoCredito1Repository consumoCredito1Repository, ModelMapper modelMapper) {
        this.moraCredito1Repository = moraCredito1Repository;
        this.credito1Repository = credito1Repository;
        this.consumoCredito1Repository = consumoCredito1Repository;
        this.modelMapper = modelMapper;
    }



    @Override
    public List<MoraCredito1Response> getAllMoraCredito1() {
        var existingMora = moraCredito1Repository.findAll();
        if (existingMora.isEmpty())
            throw new ResourceNotFoundException("No existe ninguna mora");

        var toShowMoras = existingMora.stream()
                .map(Mora -> modelMapper.map(Mora, MoraCredito1Response.class))
                .toList();

        return toShowMoras;
    }

    @Override
    public List<MoraCredito1Response> getMoraCredito1ByCreditoId(Long creditoId) {

        // Buscar si existe en el credito
        var existingCredito = credito1Repository.findById(creditoId);
        if (existingCredito.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el credito1 con id " + creditoId);
        }

        //obtine los productos asociados al creditoId
        var existingMoraCredito1 = moraCredito1Repository.findMoraByCredito1Id(creditoId);
        if (existingMoraCredito1.isEmpty())
            throw new ResourceNotFoundException("No existe ninguna mora asociada para el credito con id "+ creditoId);

        // Muestra la lista de mora asociados al credito
        var toShowMora= existingMoraCredito1.stream()
                .map(Mora -> modelMapper.map(Mora, MoraCredito1Response.class))
                .toList();

        return toShowMora;
    }

    @Override
    public MoraCredito1Response createMoraCredito1(ConsumoCredito1 consumoCredito1, ConsumoCredito1Request consumo, MoraCredito1Request moracredito1Request, Long creditoId) {
        // Buscar si mora en el credito
        var existingCredito1 = credito1Repository.findById(creditoId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un credito1 con ID: " + creditoId));

        // Validación
        MoraCreditos1Validation.ValidateMoraCredito1(moracredito1Request);

        // Mapeo
        var newMora = modelMapper.map(moracredito1Request, MoraCredito1.class);

        newMora.setCredito1(existingCredito1); //asocia el consumo a un credito1

        //obtener datos
        String TEPm = newMora.getTEPm();
        Long tasa = newMora.getTasa();
        Long dias_atraso = newMora.getDias_atraso();


        //setear datos
        Long totalMoras = TasaEfectivaPeriodo.calcularInteresMoratorio(TEPm, tasa, sumTotalConsumoByCreditoId(creditoId), dias_atraso);
        newMora.setTotal_moras(totalMoras);


        var createMora = moraCredito1Repository.save(newMora);
        var response = modelMapper.map(createMora, MoraCredito1Response.class);

        return response;
    }


    @Override
    public Long sumTotalConsumoByCreditoId(Long creditoId) {
        return consumoCredito1Repository.sumTotalConsumoByCreditoId(creditoId);
    }


    @Override
    public MoraCredito1Response updateMoraCredito1(Long id, String TEPm, Long tasa, Long dias_atraso, Long total_moras, Long credito1) {
        // Buscar el mora
        var mora = moraCredito1Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro un mora con el id: " + id));


        mora.setTotal_moras(total_moras);
        mora.setTEPm(TEPm);
        mora.setDias_atraso(dias_atraso);
        mora.setTasa(tasa);


        // Guardar el moracredito1  actualizado
        var updatedreservation = moraCredito1Repository.save(mora);

        // Retornar la respuesta actualizada
        return modelMapper.map(updatedreservation, MoraCredito1Response.class);
    }

    @Override
    public MoraCredito1Response deleteMoraCredito1(Long id) {

        // Buscar la mora
        var mora = moraCredito1Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una mora con el id: " + id));

        // Eliminar la mora
        moraCredito1Repository.delete(mora);

        // Mapeo de la respuesta para confirmar eliminación
        var response = modelMapper.map(mora, MoraCredito1Response.class);

        return response;
    }




}
