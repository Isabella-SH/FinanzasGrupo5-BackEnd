package com.example.finanzasgrupo5backend.Credito1.Service;

import com.example.finanzasgrupo5backend.Credito1.Model.*;
import com.example.finanzasgrupo5backend.Credito1.Repository.IConsumoCredito1Repository;
import com.example.finanzasgrupo5backend.Credito1.Repository.ICredito1Repository;
import com.example.finanzasgrupo5backend.Credito1.Repository.IMoraCredito1Repository;
import com.example.finanzasgrupo5backend.Credito1.Repository.IPagoCredito1Repository;
import com.example.finanzasgrupo5backend.Shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Qualifier("pagoCredito1ServiceImpl")
public class PagoCredito1ServiceImpl implements IPagoCredito1Service{
    @Autowired
    private  IMoraCredito1Repository moraCredito1Repository;
    @Autowired
    private  ICredito1Repository credito1Repository;
    @Autowired
    private  IConsumoCredito1Repository consumoCredito1Repository;
    @Autowired
    private  IPagoCredito1Repository pagoCredito1Repository;
    private  ModelMapper modelMapper;

    public PagoCredito1ServiceImpl(IMoraCredito1Repository moraCredito1Repository, ICredito1Repository credito1Repository, IConsumoCredito1Repository consumoCredito1Repository, IPagoCredito1Repository pagoCredito1Repository, ModelMapper modelMapper) {
        this.moraCredito1Repository = moraCredito1Repository;
        this.credito1Repository = credito1Repository;
        this.consumoCredito1Repository = consumoCredito1Repository;
        this.pagoCredito1Repository = pagoCredito1Repository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<PagoCredito1Response> getAllPagoCredito1() {
        var existingPago = pagoCredito1Repository.findAll();
        if (existingPago.isEmpty())
            throw new ResourceNotFoundException("No existe ningun pago");

        var toShowPagos = existingPago.stream()
                .map(Pago -> modelMapper.map(Pago, PagoCredito1Response.class))
                .toList();

        return toShowPagos;
    }

    @Override
    public List<PagoCredito1Response> getPagoCredito1ByCreditoId(Long creditoId) {
        // Buscar si existe en el credito
        var existingCredito1 = credito1Repository.findById(creditoId);
        if (existingCredito1.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el credito1 con id " + creditoId);
        }

        //obtine los productos asociados al creditoId
        var existingPagos = pagoCredito1Repository.findPagoByCredito1Id(creditoId);
        if (existingPagos.isEmpty())
            throw new ResourceNotFoundException("No existe ningun pago para el credito con id "+ creditoId);

        // Muestra la lista de productos asociados al store
        var toShowPagos = existingPagos.stream()
                .map(Pago -> modelMapper.map(Pago, PagoCredito1Response.class))
                .toList();

        return toShowPagos;
    }

    @Override
    public PagoCredito1Response createPagoCredito1(Long creditoId) {

        // Buscar el pago en el credito
        var existingCredito1 = credito1Repository.findById(creditoId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un credito1 con ID: " + creditoId));

        var existingMoraCredito1 = moraCredito1Repository.findMoraByCredito1Id(creditoId);



        // Mapeo
        var newPago = new PagoCredito1();

        newPago.setCredito1(existingCredito1); //asocia el consumo a un credito1

        // obtener datos
        Double total_moras = 0D;

        if (!existingMoraCredito1.isEmpty())
        {
            total_moras = existingMoraCredito1.stream().mapToDouble(MoraCredito1::getTotal_moras).sum();
        }

        newPago.setTotal_moras(total_moras);


        Double total_monto_consumos = consumoCredito1Repository.sumTotalConsumoByCreditoId(creditoId);
        newPago.setTotal_monto_consumos(total_monto_consumos);

        Double monto_pagar = (Double) (total_moras + total_monto_consumos);
        newPago.setMonto_a_pagar(monto_pagar);
        newPago.setCredito1(existingCredito1);

        var createPago = pagoCredito1Repository.save(newPago);
        var response = modelMapper.map(createPago, PagoCredito1Response.class);

        return response;
    }



    @Override
    public PagoCredito1Response updatePagoCredito1(Long id, Double total_moras, Double total_monto_consumos, Double monto_a_pagar, Boolean pagado, Long credito1, Long moracredito1) {
        // Buscar el pago
        var pago = pagoCredito1Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro un pago con el id: " + id));

        pago.setTotal_monto_consumos(total_monto_consumos);
        pago.setTotal_moras(total_moras);
        pago.setMonto_a_pagar(monto_a_pagar);


        // Guardar el pagocredito1  actualizado
        var updatedreservation = pagoCredito1Repository.save(pago);

        // Retornar la respuesta actualizada
        return modelMapper.map(updatedreservation, PagoCredito1Response.class);
    }

    @Override
    public PagoCredito1Response deletePagoCredito1(Long id) {

        // Buscar el pago
        var pago = pagoCredito1Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un pago con el id: " + id));

        // Eliminar el pago
        pagoCredito1Repository.delete(pago);

        // Mapeo de la respuesta para confirmar eliminación
        var response = modelMapper.map(pago, PagoCredito1Response.class);

        return response;
    }
}
