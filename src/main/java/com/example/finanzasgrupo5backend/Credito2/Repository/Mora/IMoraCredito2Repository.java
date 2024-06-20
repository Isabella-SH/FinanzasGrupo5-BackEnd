package com.example.finanzasgrupo5backend.Credito2.Repository.Mora;

import com.example.finanzasgrupo5backend.Credito2.Model.Mora.MoraCredito2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMoraCredito2Repository extends JpaRepository<MoraCredito2,Long> {

    List<MoraCredito2> findMoraByCredito2Id(Long idCredito2);
    List<MoraCredito2> findAll();
    List<MoraCredito2> findById(long id);
    //MoraCredito2 findNumeroCuota(double numero_cuota);

    @Query(value = "SELECT sum(total_moras) FROM mora_credito2 mc" +
            "            where mc.credito2_id = :id and mc.numero_cuota = :cuotas" +
            "            group by mc.credito2_id", nativeQuery = true)
    Double sumTotalMoraByCreditoIdAndCuota(@Param("id")Long creditoId,@Param("cuotas") Long cuota);
}