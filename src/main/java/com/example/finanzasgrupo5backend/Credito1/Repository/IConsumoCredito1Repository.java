package com.example.finanzasgrupo5backend.Credito1.Repository;

import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1;
import com.example.finanzasgrupo5backend.Products.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "SELECT sum(monto_consumo) FROM consumo_credito1 as cc" +
            "            join creditos1 as cd on cd.id = cc.id_creditos1" +
            "            join client as cl on cl.id = cd.client" +
            "            where cc.id_creditos1 = :id" +
            "            group by cc.id_creditos1", nativeQuery = true)
    Long sumTotalConsumoByCreditoId(@Param("id") Long creditoId);
}
