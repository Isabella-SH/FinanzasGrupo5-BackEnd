package com.example.finanzasgrupo5backend.Profile.Clients.Repository;

import com.example.finanzasgrupo5backend.Profile.Clients.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientRepository extends JpaRepository<Client,Long> {

    List<Client> findClientByUser(Long user_id);
    List<Client> findAll();

    List<Client> findById(long id);

}
