package com.example.finanzasgrupo5backend.Profile.Clients.Service;

import com.example.finanzasgrupo5backend.Profile.Clients.Model.Client;
import com.example.finanzasgrupo5backend.Profile.Clients.Model.ClientRequest;
import com.example.finanzasgrupo5backend.Profile.Clients.Model.ClientResponse;

public interface IClientService {
    public abstract ClientResponse createClient(Long user_id, Long store_id, ClientRequest clientRequest);

    public abstract void deleteClient(Long id_client);

    public abstract ClientResponse updateClient(Long id, Long credit_limit);

}
