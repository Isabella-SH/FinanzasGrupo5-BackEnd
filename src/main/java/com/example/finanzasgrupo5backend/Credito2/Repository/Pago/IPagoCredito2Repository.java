package com.example.finanzasgrupo5backend.Credito2.Repository.Pago;

import com.example.finanzasgrupo5backend.Credito2.Model.Pago.PagoCredito2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPagoCredito2Repository extends JpaRepository<PagoCredito2, Long> {

}
