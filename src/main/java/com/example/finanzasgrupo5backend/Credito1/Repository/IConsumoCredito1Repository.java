package com.example.finanzasgrupo5backend.Credito1.Repository;

import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1;
import com.example.finanzasgrupo5backend.Products.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IConsumoCredito1Repository extends JpaRepository<ConsumoCredito1,Long> {

    List<ConsumoCredito1> findConsumoCredito1ByCredito1(Long creditoId);

    List<ConsumoCredito1> findAll();

    List<ConsumoCredito1> findById(long id);

    List<ConsumoCredito1> findByFechaFinal(LocalDate fechaInicial);

    List<ConsumoCredito1> findByFechaInicial(LocalDate fechaFinal);

}
