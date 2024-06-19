package com.example.finanzasgrupo5backend.Credito1.Service;


import com.example.finanzasgrupo5backend.Profile.Clients.Repository.IClientRepository;
import com.example.finanzasgrupo5backend.Credito1.Model.Credito1;
import com.example.finanzasgrupo5backend.Credito1.Model.Credito1Request;
import com.example.finanzasgrupo5backend.Credito1.Model.Credito1Response;
import com.example.finanzasgrupo5backend.Credito1.Repository.ICredito1Repository;
import com.example.finanzasgrupo5backend.Shared.exception.ResourceNotFoundException;
import com.example.finanzasgrupo5backend.Validations.Credito1.Credito1Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Qualifier("credito1ServiceImpl")
public class Credito1ServiceImpl implements ICredito1Service {

    private final ICredito1Repository credito1Repository;
    private final IClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public Credito1ServiceImpl(ICredito1Repository credito1Repository, IClientRepository clientRepository, ModelMapper modelMapper){
        this.modelMapper=modelMapper;
        this.credito1Repository=credito1Repository;
        this.clientRepository=clientRepository;
    }


    @Override
    public List<Credito1Response> getAllCredito1() {
        var existingCredito1 = credito1Repository.findAll();
        if (existingCredito1.isEmpty())
            throw new ResourceNotFoundException("No existe ningun credito 1");

        var toShowCreditos1 = existingCredito1.stream()
                .map(Credito1 -> modelMapper.map(Credito1, Credito1Response.class))
                .toList();

        return toShowCreditos1;
    }

    @Override
    public List<Credito1Response> getCreditos1ByClienteId(Long clienteId){

        // Buscar si existe el store
        var existingcliente = clientRepository.findById(clienteId);
        if (existingcliente.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el cliente con id " + clienteId);
        }

        //obtine los productos asociados al storeid
        var existingCredito1 = credito1Repository.findCredito1ByClientId(clienteId);
        if (existingCredito1.isEmpty())
            throw new ResourceNotFoundException("No existe ningun credito para el cliente con id "+clienteId);

        // Muestra la lista de productos asociados al store
        var toShowCreditos1 = existingCredito1.stream()
                .map(Credito1 -> modelMapper.map(Credito1, Credito1Response.class))
                .toList();

        return toShowCreditos1;
    }




    //POST
    @Override
    public Credito1Response createCredito1(Credito1Request credito1Request, Long clienteId) {

        // Buscar el negocioalq ue pertenece
        var existingcliente = clientRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el un cliente con ID: " + clienteId));

        // Validación
        Credito1Validation.ValidateCredito1(credito1Request);

        // Mapeo
        var newCredito1 = modelMapper.map(credito1Request, Credito1.class);

        newCredito1.setClient(existingcliente); //asocia el producto a un store

        var createCredito1 = credito1Repository.save(newCredito1);
        var response = modelMapper.map(createCredito1, Credito1Response.class);

        return response;
    }

    @Override
    public Credito1Response updateCredito1(Long id, LocalDate fechaInicial, LocalDate fechaFinal, String TEoN,String TEP, String TNP,
                                           Double tasa, String perio_capitalizacion, Long clienteId) {

        // Buscar el producto
        var credito1 = credito1Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro un credito1 con el id: " + id));


        credito1.setTEoN(TEoN);
        credito1.setTEP(TEP);
        credito1.setTNP(TNP);
        credito1.setTasa(tasa);
        credito1.setFechaFinal(fechaFinal);
        credito1.setFechaInicial(fechaInicial);
        credito1.setPerio_capitalizacion(perio_capitalizacion);


        // Guardar el credito1 actualizado
        var updatedreservation = credito1Repository.save(credito1);

        // Retornar la respuesta actualizada
        return modelMapper.map(updatedreservation, Credito1Response.class);

    }


    @Override
    public Credito1Response deleteCredito1(Long id) {
        // Buscar el producto
        var credito1 = credito1Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un credito con el id: " + id));

        // Eliminar el credito
        credito1Repository.delete(credito1);

        // Mapeo de la respuesta para confirmar eliminación
        var response = modelMapper.map(credito1, Credito1Response.class);

        return response;
    }

}
