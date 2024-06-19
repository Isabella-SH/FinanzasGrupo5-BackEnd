package com.example.finanzasgrupo5backend.Credito2.Repository.Consumo;

import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IConsumoCredito2Repository extends JpaRepository<ConsumoCredito2, Long> {

    List<ConsumoCredito2> findConsumoCredito2sById(Long id);
}
