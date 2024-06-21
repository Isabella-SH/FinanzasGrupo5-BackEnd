package com.example.finanzasgrupo5backend.Credito2.Repository.Pago;

import com.example.finanzasgrupo5backend.Credito2.Model.Credito.Credito2;
import com.example.finanzasgrupo5backend.Credito2.Model.Pago.PagoCredito2;
import com.example.finanzasgrupo5backend.Credito2.Model.Pago.PagoCredito2Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPagoCredito2Repository extends JpaRepository<PagoCredito2, Long> {

    @Query(value = "select pc.id, pc.renta , pc.total_moras, " +
            "pc.monto_a_pagar, pc.cuota, " +
            "pc.estado, pc.credito2_id " +
            "from pago_Credito2 pc " +
            "where pc.credito2_id = :cred "  , nativeQuery = true)
    List<String[]> getPagoCredito2(@Param("cred")Long credito2Id);

    @Query(value = "select coalesce(sum(pd.price),0) suma " +
            "from pago_credito2 pc " +
            "join creditos2 cd on cd.id = pc.credito2_id " +
            "join consumo_credito2 cc on cc.cuota = pc.cuota " +
            "join products pd on pd.id = cc.producto " +
            "where pc.credito2_id = :cred and pc.id = :pago " +
            "group by pc.cuota, pc.id, pc.credito2_id", nativeQuery = true)
    Double sumTotalProductosPago(@Param("cred") Long creditoId, @Param("pago") Long pago);
}
