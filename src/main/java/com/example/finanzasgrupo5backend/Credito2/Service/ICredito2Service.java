package com.example.finanzasgrupo5backend.Credito2.Service;

import com.example.finanzasgrupo5backend.Credito2.Model.Credito.Credito2Request;
import com.example.finanzasgrupo5backend.Credito2.Model.Credito.Credito2Response;

import java.time.LocalDate;
import java.util.List;

public interface ICredito2Service {


    List<Credito2Response> getAllCreditos2();

    List<Credito2Response>  getCreditos2ByClienteId(Long clienteId);

    Credito2Response createCreditos2(Credito2Request credito2Request, Long clienteId);

   Credito2Response updateCredito2(Long id, LocalDate fechaInicial, LocalDate fechaFinal, String TEoN, String TEP, String TNP,
                                                    Double tasa, Double renta, Long cuotas, Long dias_plazo_gracia, Long clienteId);
   Credito2Response deleteCredito2(Long id);
}
