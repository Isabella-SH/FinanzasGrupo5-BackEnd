package com.example.finanzasgrupo5backend.Credito1.Repository;


import com.example.finanzasgrupo5backend.Credito1.Model.MoraCredito1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface IMoraCredito1Repository extends JpaRepository<MoraCredito1,Long> {



    List<MoraCredito1> findMoraByCredito1Id(Long storeId);
    List<MoraCredito1> findAll();

    List<MoraCredito1> findById(long id);









}
