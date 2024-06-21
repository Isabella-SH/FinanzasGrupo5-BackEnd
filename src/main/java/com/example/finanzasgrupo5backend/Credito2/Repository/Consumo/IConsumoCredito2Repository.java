package com.example.finanzasgrupo5backend.Credito2.Repository.Consumo;

import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IConsumoCredito2Repository extends JpaRepository<ConsumoCredito2, Long> {

    List<ConsumoCredito2> findConsumoCredito2sById(Long id);

    @Query(value = "SELECT cons.id, pd.name, pd.price, cons.fecha_inicial, " +
            "cons.fecha_final, cons.cuota, cons.credito2" +
            "from consumo_credito2 cons" +
            "join products pd on pd.id = cons.producto" +
            "where cons.credito2 = :id", nativeQuery = true)
    List<String[]> listConsumosByCredito(@Param("id") Long creditoId);
}
