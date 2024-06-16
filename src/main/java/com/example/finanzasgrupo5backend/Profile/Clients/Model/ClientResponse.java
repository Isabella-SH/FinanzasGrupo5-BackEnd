package com.example.finanzasgrupo5backend.Profile.Clients.Model;

import com.example.finanzasgrupo5backend.Credito1.Model.Credito1;
import com.example.finanzasgrupo5backend.Profile.Store.Model.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClientResponse {

    private Long id;
    private String name;
    private Long dni;
    private String email;
    private Long credit_limit;


}
