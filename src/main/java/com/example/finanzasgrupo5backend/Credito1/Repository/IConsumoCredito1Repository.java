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

    //suma total por credito
    @Query("SELECT sum(monto_consumo) FROM consumo_credito1 WHERE credito1 = :creditoId")
    Long sumTotalConsumoByCredito1(@Param("creditoId") Long creditoId);


    //suma total de consumos por id del cliente
    @Query("SELECT sum(monto_consumo) FROM consumo_credito1 as cc" +
            "join creditos1 as cd on cd.id = cc.creditos1" +
            "join clients as cl on cl.id = cd.client" +
            "where cl.id = :clientId" +
            "group by cl.id")
    Long sumTotalConsumoByClientId(@Param("clientId") Long clientId);

    //suma total de consumos por id de cliente y por id de credito
    @Query("SELECT sum(monto_consumo) FROM consumo_credito1 as cc" +
            "join creditos1 as cd on cd.id = cc.creditos1" +
            "join clients as cl on cl.id = cd.client" +
            "where cl.id = :clientId and cd.id = :creditoId" +
            "group by cl.id")
    Long sumTotalConsumoByClientIdAndCreditoId(@Param("clientId") Long clientId, @Param("creditoId") Long creditoId);
}
