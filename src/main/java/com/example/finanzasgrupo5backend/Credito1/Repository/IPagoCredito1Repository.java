package com.example.finanzasgrupo5backend.Credito1.Repository;


import com.example.finanzasgrupo5backend.Credito1.Model.MoraCredito1;
import com.example.finanzasgrupo5backend.Credito1.Model.PagoCredito1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPagoCredito1Repository extends JpaRepository<PagoCredito1,Long> {

    List<PagoCredito1> findPagoByCredito1Id(Long creditoId);

    List<PagoCredito1> findAll();

    List<PagoCredito1> findById(long id);

}
