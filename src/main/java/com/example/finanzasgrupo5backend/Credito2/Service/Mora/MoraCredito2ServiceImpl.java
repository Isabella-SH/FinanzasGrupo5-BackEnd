package com.example.finanzasgrupo5backend.Credito2.Service.Mora;

import com.example.finanzasgrupo5backend.Credito1.Model.MoraCredito1Response;
import com.example.finanzasgrupo5backend.Credito2.Model.Mora.MoraCredito2;
import com.example.finanzasgrupo5backend.Credito2.Model.Mora.MoraCredito2Request;
import com.example.finanzasgrupo5backend.Credito2.Model.Mora.MoraCredito2Response;
import com.example.finanzasgrupo5backend.Credito2.Repository.Consumo.IConsumoCredito2Repository;
import com.example.finanzasgrupo5backend.Credito2.Repository.ICredito2Repository;
import com.example.finanzasgrupo5backend.Credito2.Repository.Mora.IMoraCredito2Repository;
import com.example.finanzasgrupo5backend.Formulas.Anualidades.SimpleVencida.AnualidadSimpleVencida;
import com.example.finanzasgrupo5backend.Formulas.ValorFuturo.TEP.TasaEfectivaPeriodo;
import com.example.finanzasgrupo5backend.Shared.exception.ResourceNotFoundException;
import com.example.finanzasgrupo5backend.Validations.Credito2.MoraCreditos2Validation;
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
        var existingMora = moraCredito2Repository.findAll();
        if (existingMora.isEmpty())
            throw new ResourceNotFoundException("No existe ninguna mora");

        var toShowMoras = existingMora.stream()
                .map(Mora -> modelMapper.map(Mora, MoraCredito2Response.class))
                .toList();

        return toShowMoras;
    }

    @Override
    public List<MoraCredito2Response> getMoraCredito2ByCreditoId(Long credito2) {

        // Buscar si existe en el credito
        var existingCredito = credito2Repository.findById(credito2);
        if (existingCredito.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el credito anualidad con id " + credito2);
        }

        //obtine las moras asociados al creditoId
        var existingMoraCredito2 = moraCredito2Repository.findMoraByCredito2Id(credito2);
        if (existingMoraCredito2.isEmpty())
            throw new ResourceNotFoundException("No existe ninguna mora asociada para el credito anualidad con id "+ credito2);

        // Muestra la lista de mora asociados al credito
        var toShowMora= existingMoraCredito2.stream()
                .map(Mora -> modelMapper.map(Mora, MoraCredito2Response.class))
                .toList();

        return toShowMora;
    }

    @Override
    public MoraCredito2Response createMoraCredito2(MoraCredito2Request moracredito2Request, Long credito2Id) {

        // Buscar si existe el credito
        var existingCredito2 = credito2Repository.findById(credito2Id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un credito anualidad con ID: " + credito2Id));

        // Validación
        MoraCreditos2Validation.ValidateMoraCredito2(moracredito2Request);

        // Mapeo
        var newMora = modelMapper.map(moracredito2Request, MoraCredito2.class);

        //asocia el consumo a un credito2
        newMora.setCredito2(existingCredito2);

        //valida que la cuota a la que pertenece la mora sea valida
        MoraCreditos2Validation.ValidateNumeroCuotaCorrecto(moracredito2Request, existingCredito2.getCuotas(),credito2Id, moraCredito2Repository);

        System.out.println(moracredito2Request);
        System.out.println(newMora.getDias_atraso());

        //obtener datos de la mora
        String TEPm = newMora.getTEPm();
        double tasa = newMora.getTasa();
        double dias_atraso = newMora.getDias_atraso();

        //obtener datos de su credito asociado
        String TEPcredito = newMora.getCredito2().getTEP();
        double tasaCredito = newMora.getCredito2().getTasa();
        double renta=newMora.getCredito2().getRenta();

        //usar formulas
        Double interes_compensatorio = AnualidadSimpleVencida.calcularInteresCompensatorio(TEPcredito, tasaCredito
                , renta, dias_atraso);
        Double interes_moratorio = AnualidadSimpleVencida.calcularInteresMoratorio(TEPm, tasa, renta, dias_atraso);

        //setear datos
        double totalMoras= interes_compensatorio+interes_moratorio;
        newMora.setTotal_moras(totalMoras);

        var createMora = moraCredito2Repository.save(newMora);
        var response = modelMapper.map(createMora, MoraCredito2Response.class);

        return response;
    }

    @Override
    public MoraCredito2Response updateMoraCredito2(Long id, String TEPm, Double tasa, Double dias_atraso) {

        // Buscar el mora
        var mora = moraCredito2Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro un mora con el id: " + id));

        //setea nuevos valores de mora
        mora.setTEPm(TEPm);
        mora.setDias_atraso(dias_atraso);
        mora.setTasa(tasa);

        //obtiene datos
        String tep_credito= mora.getCredito2().getTEP();
        double tasa_credito= mora.getCredito2().getTasa();
        double renta= mora.getCredito2().getTasa();

        //usar formulas
        Double interes_compensatorio = AnualidadSimpleVencida.calcularInteresCompensatorio(tep_credito, tasa_credito
                , renta, dias_atraso);
        Double interes_moratorio = AnualidadSimpleVencida.calcularInteresMoratorio(TEPm, tasa, renta, dias_atraso);

        //setear datos
        double totalMoras= interes_compensatorio+interes_moratorio;
        mora.setTotal_moras(totalMoras);

        // Guardar el moracredito2  actualizado
        var updatedreservation = moraCredito2Repository.save(mora);

        // Retornar la respuesta actualizada
        return modelMapper.map(updatedreservation, MoraCredito2Response.class);
    }

    @Override
    public MoraCredito2Response deleteMoraCredito2(Long id) {
        // Buscar la mora
        var mora = moraCredito2Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una mora con el id: " + id));

        // Eliminar la mora
        moraCredito2Repository.delete(mora);

        // Mapeo de la respuesta para confirmar eliminación
        var response = modelMapper.map(mora, MoraCredito2Response.class);

        return response;
    }
}
