package com.example.finanzasgrupo5backend.Profile.Clients.Model;

import com.example.finanzasgrupo5backend.Profile.Store.Model.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {

        private Long id;
        private String name;
        private Long dni;
        private String email;

}
