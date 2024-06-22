package com.example.finanzasgrupo5backend.Profile.Clients.Controller;

import com.example.finanzasgrupo5backend.Products.Model.ProductResponse;
import com.example.finanzasgrupo5backend.Profile.Clients.Model.Client;
import com.example.finanzasgrupo5backend.Profile.Clients.Model.ClientRequest;
import com.example.finanzasgrupo5backend.Profile.Clients.Model.ClientResponse;
import com.example.finanzasgrupo5backend.Profile.Clients.Service.IClientService;
import com.example.finanzasgrupo5backend.Profile.Store.Model.Store;
import com.example.finanzasgrupo5backend.Profile.Store.Model.StoreRequest;
import com.example.finanzasgrupo5backend.Profile.Store.Model.StoreResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Client Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @Operation(summary = "Create a Client")
    @PostMapping("/clients")
    public ResponseEntity<ClientResponse> ingresar(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "storeId") Long storeId,
            @RequestBody ClientRequest clientRequest){
        clientService.createClient(userId, storeId, clientRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Update Client's Credit Limit")
    @PatchMapping("/clients")
    public ResponseEntity<ClientResponse> actualizar(
            @RequestParam(name = "clientId") Long id){

        clientService.updateClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Obtain a list of all Clientes")
    @GetMapping("/clients")
    public ResponseEntity<List<ClientResponse>> getAllClientes() {
        var res = clientService.getAllClients();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Obtain a list of clientes by user Id")
    @GetMapping("/clients/user/{id}")
    public ResponseEntity<List<ClientResponse>> getClientByUserId(@PathVariable(name = "id") Long userId) {
        var res = clientService.getClientByUser(userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete a Store")
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable(name = "id") Long clientId) {
        clientService.deleteCliente(clientId);
        return ResponseEntity.noContent().build();
    }





}
