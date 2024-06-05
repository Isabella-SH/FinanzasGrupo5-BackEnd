package com.example.finanzasgrupo5backend.Credito1.Repository;

import com.example.finanzasgrupo5backend.Credito1.Model.Credito1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICredito1Repository extends JpaRepository<Credito1,Long> {

    List<Credito1> findCredito1ByClientId(Long storeId);
    List<Credito1> findAll();

    List<Credito1> findById(long id);
}
