package com.example.finanzasgrupo5backend.Clients.Repository;

import com.example.finanzasgrupo5backend.Clients.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client,Long> {
}
