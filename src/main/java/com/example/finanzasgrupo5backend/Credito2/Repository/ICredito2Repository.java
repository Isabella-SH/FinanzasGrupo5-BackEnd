package com.example.finanzasgrupo5backend.Credito2.Repository;


import com.example.finanzasgrupo5backend.Credito2.Model.Credito.Credito2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICredito2Repository extends JpaRepository<Credito2,Long> {

    List<Credito2> findCredito2ByClientId(Long clientd);
    List<Credito2> findAll();

    List<Credito2> findById(long id);
}
