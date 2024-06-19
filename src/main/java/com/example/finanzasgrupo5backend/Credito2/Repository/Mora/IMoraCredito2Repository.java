package com.example.finanzasgrupo5backend.Credito2.Repository.Mora;

import com.example.finanzasgrupo5backend.Credito2.Model.Mora.MoraCredito2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMoraCredito2Repository extends JpaRepository<MoraCredito2,Long> {

    List<MoraCredito2> findMoraByCredito2Id(Long idCredito2);
    List<MoraCredito2> findAll();
    List<MoraCredito2> findById(long id);
    MoraCredito2 findNumeroCuota(double numero_cuota);
}