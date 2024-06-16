package com.example.finanzasgrupo5backend.Profile.Clients.Service;

import com.example.finanzasgrupo5backend.Products.Model.Product;
import com.example.finanzasgrupo5backend.Products.Model.ProductResponse;
import com.example.finanzasgrupo5backend.Profile.Clients.Model.Client;
import com.example.finanzasgrupo5backend.Profile.Clients.Model.ClientRequest;
import com.example.finanzasgrupo5backend.Profile.Clients.Model.ClientResponse;
import com.example.finanzasgrupo5backend.Profile.Clients.Repository.IClientRepository;
import com.example.finanzasgrupo5backend.Profile.Store.Repository.IStoreRepository;
import com.example.finanzasgrupo5backend.Shared.exception.ResourceNotFoundException;
import com.example.finanzasgrupo5backend.Users.Repository.IUserRepository;
import com.example.finanzasgrupo5backend.Validations.ProductValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
    public ClientResponse createClient(Long user_id, Long store_id, ClientRequest clientRequest) {
// Buscar el negocioalq ue pertenece
        var existingStore = storeRepository.findById(store_id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el un store con ID: " + store_id));

        var existingUser = userRepository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el un user con ID: " + user_id));

        // Mapeo
        var newClient = modelMapper.map(clientRequest, Client.class);

        newClient.setStore_id(existingStore); //asocia el producto a un store
        newClient.setUser_id(existingUser); //asocia el producto a un store

        var createClient = clientRepository.save(newClient);
        var response = modelMapper.map(createClient, ClientResponse.class);

        return response;
    }

    @Override
    public void deleteClient(Long id_client) {
        clientRepository.deleteById(id_client);
    }

    @Override
    public ClientResponse updateClient(Long id, Long credit_limit) {
        var existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el un cliente con ID: " + id));


        existingClient.setCredit_limit(credit_limit);

        var updateClient = clientRepository.save(existingClient);
        var response = modelMapper.map(updateClient, ClientResponse.class);

        return response;
    }
}
