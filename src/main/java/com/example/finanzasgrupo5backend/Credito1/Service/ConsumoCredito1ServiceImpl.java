package com.example.finanzasgrupo5backend.Credito1.Service;

import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1;
import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1Request;
import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1Response;
import com.example.finanzasgrupo5backend.Credito1.Repository.IConsumoCredito1Repository;
import com.example.finanzasgrupo5backend.Credito1.Repository.ICredito1Repository;
import com.example.finanzasgrupo5backend.Formulas.ValorFuturo.TEP.TasaEfectivaPeriodo;
import com.example.finanzasgrupo5backend.Formulas.ValorFuturo.TNP.TasaNominalPeriodo;
import com.example.finanzasgrupo5backend.Products.Model.Product;
import com.example.finanzasgrupo5backend.Products.Model.ProductResponse;
import com.example.finanzasgrupo5backend.Shared.exception.ResourceNotFoundException;
import com.example.finanzasgrupo5backend.Validations.ConsumoCredito1Validation;
import com.example.finanzasgrupo5backend.Validations.ProductValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Qualifier("consumoCredito1ServiceImpl")
public class ConsumoCredito1ServiceImpl implements IConsumoCredito1Service{

    private final IConsumoCredito1Repository consumoCredito1Repository;
    private final ICredito1Repository credito1Repository;
    private final ModelMapper modelMapper;

    public ConsumoCredito1ServiceImpl(IConsumoCredito1Repository consumoCredito1Repository, ICredito1Repository credito1Repository, ModelMapper modelMapper) {
        this.consumoCredito1Repository = consumoCredito1Repository;
        this.credito1Repository = credito1Repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ConsumoCredito1Response> getAllConsumosCredito1() {
        var existingConsumos = consumoCredito1Repository.findAll();
        if (existingConsumos.isEmpty())
            throw new ResourceNotFoundException("No existe ningun consumo");

        var toShowConsumos = existingConsumos.stream()
                .map(Consumo -> modelMapper.map(Consumo, ConsumoCredito1Response.class))
                .toList();

        return toShowConsumos;
    }

    @Override
    public List<ConsumoCredito1Response> getConsumosByCredito1Id(Long creditoId) {

        // Buscar si existe en el credito
        var existingCredito1 = credito1Repository.findById(creditoId);
        if (existingCredito1.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el credito1 con id " + creditoId);
        }

        //obtine los productos asociados al creditoId
        var existingConsumos = consumoCredito1Repository.findConsumoCredito1ByCredito1(creditoId);
        if (existingConsumos.isEmpty())
            throw new ResourceNotFoundException("No existe ningun consumo para el credito con id "+ creditoId);

        // Muestra la lista de productos asociados al store
        var toShowConsumos = existingConsumos.stream()
                .map(Consumo -> modelMapper.map(Consumo, ConsumoCredito1Response.class))
                .toList();

        return toShowConsumos;
    }

    //falta corregir
    @Override
    public List<ConsumoCredito1Response> getConsumosByCreditoFechaInicioAndFechaFin(Long creditoId, LocalDate fechaInicial, LocalDate fechaFinal) {

        //obtener los consumos del credito de un cliente especifico  ????????

        //obtine los productos en el rango de fechas
        var existingConsumosFechainicial = consumoCredito1Repository.findByFechaInicial(fechaInicial);
        if (existingConsumosFechainicial.isEmpty())
            throw new ResourceNotFoundException("No existe consumos con fecha inicial "+ fechaInicial);

        var existingConsumosFechaFinal = consumoCredito1Repository.findByFechaFinal(fechaFinal);
        if (existingConsumosFechaFinal.isEmpty())
            throw new ResourceNotFoundException("No existe consumos con fecha final "+ fechaFinal);

        // Combinar las listas de consumos inicial y final
        var combinedConsumos = Stream.concat(existingConsumosFechainicial.stream(), existingConsumosFechaFinal.stream())
                .distinct() // Elimina duplicados si hay consumos que aparecen en ambas listas
                .collect(Collectors.toList());

        // Mapear los consumos combinados a ConsumoCredito1Response
        var existingConsumosFechas = combinedConsumos.stream()
                .map(consumo -> modelMapper.map(consumo, ConsumoCredito1Response.class))
                .collect(Collectors.toList());

        return existingConsumosFechas;
    }

    @Override
    public ConsumoCredito1Response createConsumo(ConsumoCredito1Request consumo, Long creditoId) {

        // Buscar si existe en el credito
        var existingCredito1 = credito1Repository.findById(creditoId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un credito1 con ID: " + creditoId));

        // Validación
        ConsumoCredito1Validation.validateConsumoCredito1(consumo);

        // Mapeo
        var newConsumo = modelMapper.map(consumo, ConsumoCredito1.class);

        newConsumo.setCredito1(existingCredito1); //asocia el consumo a un credito1

            //obtener datos
        LocalDate fechaI= newConsumo.getFechaInicial();
        LocalDate fechaF=newConsumo.getFechaFinal();
        long precio= newConsumo.getPrecio();

        long tasa= newConsumo.getCredito1().getTasa();
        String tep= newConsumo.getCredito1().getTEP();
        String tnp= newConsumo.getCredito1().getTNP();
        String periodoCapi= newConsumo.getCredito1().getPerio_capitalizacion();

           //setear datos
        long interesConsumo= TasaEfectivaPeriodo.interes(tasa,precio);
        newConsumo.setInteres(interesConsumo);

        if(newConsumo.getCredito1().getTEoN() == "E"){
            long montoConsumo= TasaEfectivaPeriodo.montoPagarConsumo(fechaI,fechaF, tep,tasa,precio);
            newConsumo.setMontoConsumo(montoConsumo);
        }
        else if (newConsumo.getCredito1().getTEoN() == "N") {
            long montoConsumo= TasaNominalPeriodo.montoPagarConsumo(fechaI,fechaF, tnp,tasa,precio,periodoCapi);
            newConsumo.setMontoConsumo(montoConsumo);
        }

        var createConsumoCredito1 = consumoCredito1Repository.save(newConsumo);
        var response = modelMapper.map(createConsumoCredito1, ConsumoCredito1Response.class);

        return response;
    }

    @Override
    public ConsumoCredito1Response updateConsumo(Long id, String product, Long price, Long creditoId) {
        return null;
    }

    @Override
    public ConsumoCredito1Response deleteConsumo(Long id) {

        // Buscar el consumo
        var consumo = consumoCredito1Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un consumo con el id: " + id));

        // Eliminar el producto
        consumoCredito1Repository.delete(consumo);

        // Mapeo de la respuesta para confirmar eliminación
        var response = modelMapper.map(consumo, ConsumoCredito1Response.class);

        return response;
    }

    @Override
    public Long sumTotalConsumoByClientId(Long clientId) {
        return consumoCredito1Repository.sumTotalConsumoByClientId(clientId);
    }

    @Override
    public Long sumTotalConsumoByCreditoId(Long creditoId) {
        return consumoCredito1Repository.sumTotalConsumoByCreditoId(creditoId);
    }
}
