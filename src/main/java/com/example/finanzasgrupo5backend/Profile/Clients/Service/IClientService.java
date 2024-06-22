package com.example.finanzasgrupo5backend.Profile.Clients.Service;

import com.example.finanzasgrupo5backend.Profile.Clients.Model.ClientRequest;
import com.example.finanzasgrupo5backend.Profile.Clients.Model.ClientResponse;

import java.util.List;

public interface IClientService {
    public abstract ClientResponse createClient(Long user_id, Long cliente_id, ClientRequest clientRequest);

    List<ClientResponse> getAllClients();


    List<ClientResponse> getClientByUser(Long user_id);

    ClientResponse deleteCliente(Long id);

    //sin uso en la aplicación porque en si no debería actualizar usuarios
    public abstract ClientResponse updateClient(Long id);

}
