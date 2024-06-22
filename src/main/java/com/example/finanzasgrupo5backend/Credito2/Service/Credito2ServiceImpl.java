package com.example.finanzasgrupo5backend.Credito2.Service;



import com.example.finanzasgrupo5backend.Credito2.Model.Credito.Credito2;
import com.example.finanzasgrupo5backend.Credito2.Model.Credito.Credito2Request;
import com.example.finanzasgrupo5backend.Credito2.Model.Credito.Credito2Response;
import com.example.finanzasgrupo5backend.Credito2.Repository.ICredito2Repository;
import com.example.finanzasgrupo5backend.Credito2.Repository.Mora.IMoraCredito2Repository;
import com.example.finanzasgrupo5backend.Credito2.Repository.Pago.IPagoCredito2Repository;
import com.example.finanzasgrupo5backend.Credito2.Service.Pago.IPagoCredito2Service;
import com.example.finanzasgrupo5backend.Formulas.Anualidades.SimpleVencida.AnualidadSimpleVencida;
import com.example.finanzasgrupo5backend.Profile.Clients.Repository.IClientRepository;
import com.example.finanzasgrupo5backend.Shared.exception.ResourceNotFoundException;
import com.example.finanzasgrupo5backend.Shared.exception.ValidationException;
import com.example.finanzasgrupo5backend.Validations.Credito2.Credito2Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Qualifier("credito2ServiceImpl")
public class Credito2ServiceImpl implements ICredito2Service {

    private final ICredito2Repository credito2Repository;
    private final IClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private final IPagoCredito2Service pagoCredito2Service;
    private final IMoraCredito2Repository moraCredito2Repository;

    public Credito2ServiceImpl(ICredito2Repository credito2Repository, IClientRepository clientRepository, ModelMapper modelMapper
            , IPagoCredito2Service pagoCredito2Service, IMoraCredito2Repository moraCredito2Repository ) {
        this.credito2Repository = credito2Repository;
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
        this.pagoCredito2Service = pagoCredito2Service;
        this.moraCredito2Repository = moraCredito2Repository;
    }

    @Override
    public List<Credito2Response> getAllCreditos2() {
        var existingCredito2 = credito2Repository.findAll();
        if (existingCredito2.isEmpty())
            throw new ResourceNotFoundException("No existe ningun credito 2");

        var toShowCreditos2 = existingCredito2.stream()
                .map(Credito2 -> modelMapper.map(Credito2, Credito2Response.class))
                .toList();

        return toShowCreditos2;
    }

    @Override
    public List<Credito2Response> getCreditos2ByClienteId(Long clienteId) {

        // Buscar si existe el cliente
        var existingcliente = clientRepository.findById(clienteId);
        if (existingcliente.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el cliente con id " + clienteId);
        }

        //obtine los creditos asociados al clienteid
        var existingCredito2 = credito2Repository.findCredito2ByClientId(clienteId);
        if (existingCredito2.isEmpty())
            throw new ResourceNotFoundException("No existe ningun credito para el cliente con id "+clienteId);

        // Muestra la lista de creditos asociados al cliente
        var toShowCreditos2 = existingCredito2.stream()
                .map(Credito2 -> modelMapper.map(Credito2, Credito2Response.class))
                .toList();

        return toShowCreditos2;
    }

    @Override
    public Credito2Response createCreditos2(Credito2Request credito2Request, Long clienteId) {

        // Buscar el cliente al que pertenece
        var existingcliente = clientRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el un cliente con ID: " + clienteId));


        System.out.println(credito2Request.getPlazo_cuotas_gracias());
        // Validación
        Credito2Validation.ValidateCredito2(credito2Request);

        if(credito2Request.getPlazo_cuotas_gracias()>=credito2Request.getCuotas()){
            throw new ValidationException("La cuota de periodo de gracia del credito debe ser mayor a la cuotas");
        }

        // Mapeo
        var newCredito2 = modelMapper.map(credito2Request, Credito2.class);


       newCredito2.setRenta(AnualidadSimpleVencida.calcularRentaConPeridoDeGracia(
                credito2Request.getCredito_limit(),
                credito2Request.getTasa(), credito2Request.getTEP(),
                credito2Request.getFechaInicial(), credito2Request.getFechaFinal(),
                credito2Request.getCuotas(), credito2Request.getPlazo_cuotas_gracias()));

         /*
        newCredito2.setRenta(AnualidadSimpleVencida.calcularRenta(
                credito2Request.getCredito_limit(),
                credito2Request.getTasa(), credito2Request.getTEP(),
                credito2Request.getFechaInicial(), credito2Request.getFechaFinal(),
                credito2Request.getCuotas()));

       */


        newCredito2.setPlazo_cuotas_gracia(credito2Request.getPlazo_cuotas_gracias());
        newCredito2.setClient(existingcliente); //asocia el credito a un cliente

        var createCredito2 = credito2Repository.save(newCredito2);

        var response = modelMapper.map(createCredito2, Credito2Response .class);
        System.out.println(newCredito2.getPlazo_cuotas_gracia());

        response.setPlazo_cuotas_gracias(newCredito2.getPlazo_cuotas_gracia());
        for (int i = 1; i <= credito2Request.getCuotas() - credito2Request.getPlazo_cuotas_gracias(); i++) {
            pagoCredito2Service.createPagoCredito2(createCredito2.getRenta(), 0D,
                    (long) credito2Request.getPlazo_cuotas_gracias() + i, "PENDIENTE", createCredito2.getId());
        }

        return response;
    }

    @Override
    public Credito2Response updateCredito2(Long id, LocalDate fechaInicial, LocalDate fechaFinal, String TEP, Double tasa, Double renta, Long cuotas, Long plazo_cuotas_gracia, Long clienteId) {

        // Buscar el credito
        var credito2 = credito2Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro un credito2 con el id: " + id));


        credito2.setTEP(TEP);
        credito2.setTasa(tasa);
        credito2.setFechaFinal(fechaFinal);
        credito2.setFechaInicial(fechaInicial);
        credito2.setCuotas(cuotas);
        credito2.setPlazo_cuotas_gracia(plazo_cuotas_gracia);
        credito2.setRenta(renta);

        // Guardar el credito2 actualizado
        var updatedreservation = credito2Repository.save(credito2);

        // Retornar la respuesta actualizada
        return modelMapper.map(updatedreservation, Credito2Response.class);
    }

    @Override
    public Credito2Response deleteCredito2(Long id) {

        // Buscar el credito
        var credito2 = credito2Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un credito con el id: " + id));

        // Eliminar el credito
        credito2Repository.delete(credito2);

        // Mapeo de la respuesta para confirmar eliminación
        var response = modelMapper.map(credito2, Credito2Response.class);

        return response;
    }
}
