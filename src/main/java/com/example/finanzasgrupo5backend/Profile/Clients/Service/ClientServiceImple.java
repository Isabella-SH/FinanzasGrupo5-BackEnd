package com.example.finanzasgrupo5backend.Profile.Clients.Service;

import com.example.finanzasgrupo5backend.Profile.Clients.Model.Client;
import com.example.finanzasgrupo5backend.Profile.Clients.Model.ClientRequest;
import com.example.finanzasgrupo5backend.Profile.Clients.Model.ClientResponse;
import com.example.finanzasgrupo5backend.Profile.Clients.Repository.IClientRepository;
import com.example.finanzasgrupo5backend.Profile.Store.Repository.IStoreRepository;
import com.example.finanzasgrupo5backend.Shared.exception.ResourceNotFoundException;
import com.example.finanzasgrupo5backend.Users.Repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImple implements IClientService{

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IStoreRepository storeRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ClientResponse createClient(Long user_id, Long cliente_id, ClientRequest clientRequest) {
// Buscar el negocioalq ue pertenece
        var existingStore = storeRepository.findById(cliente_id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el un store con ID: " + cliente_id));

        var existingUser = userRepository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el un user con ID: " + user_id));

        // Mapeo
        var newClient = modelMapper.map(clientRequest, Client.class);

        newClient.setStore_id(existingStore); //asocia el producto a un store
        newClient.setUser(existingUser); //asocia el producto a un store

        var createClient = clientRepository.save(newClient);
        var response = modelMapper.map(createClient, ClientResponse.class);

        return response;
    }


    @Override
    public List<ClientResponse> getAllClients() {
        var existingCliente = clientRepository.findAll();
        if (existingCliente.isEmpty())
            throw new ResourceNotFoundException("No existe ningun cliente");

        var toShowCliente = existingCliente.stream()
                .map(Cliente -> modelMapper.map(Cliente, ClientResponse.class))
                .toList();

        return toShowCliente;
    }

   @Override
    public List<ClientResponse> getClientByUser(Long userId) {
        // Buscar si existe el User
        var existingUser = userRepository.findById(userId);
        if (existingUser.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el usuario con id " + userId);
        }

        //obtine los clientes asociados al usuarioid
        var existingCliente = clientRepository.findClientByUser(userId);
        if (existingCliente.isEmpty())
            throw new ResourceNotFoundException("No existe ningun cliente para el usuario con id "+ userId);

        // Muestra la lista de cliente asociados al usuario
        var toShowCliente = existingCliente.stream()
                .map(Cliente -> modelMapper.map(Cliente, ClientResponse.class))
                .toList();

        return toShowCliente;
    }



    @Override
    public ClientResponse deleteCliente(Long id) {
        // Buscar el cliente
        var cliente = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un cliente con el id: " + id));

        // Eliminar el cliente
        clientRepository.delete(cliente);

        // Mapeo de la respuesta para confirmar eliminación
        var response = modelMapper.map(cliente, ClientResponse.class);

        return response;
    }


    @Override
    public ClientResponse updateClient(Long id) {
        var existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el un cliente con ID: " + id));

        var updateClient = clientRepository.save(existingClient);
        var response = modelMapper.map(updateClient, ClientResponse.class);

        return response;
    }
}
