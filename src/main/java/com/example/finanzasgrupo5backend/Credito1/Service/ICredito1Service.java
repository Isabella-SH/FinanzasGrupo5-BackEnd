package com.example.finanzasgrupo5backend.Credito1.Service;


import com.example.finanzasgrupo5backend.Credito1.Model.Credito1Request;
import com.example.finanzasgrupo5backend.Credito1.Model.Credito1Response;

import java.time.LocalDate;
import java.util.List;
public interface ICredito1Service {

    public abstract List<Credito1Response> getAllProducts();

    List<Credito1Response> getAllCredito1();

    List<Credito1Response>  getCreditos1ByClienteId(Long clienteId);

    public abstract Credito1Response createCredito1(Credito1Response credito1, Long clienteId);
    //POST

    //POST
    Credito1Response createCredito1(Credito1Request credito1Request, Long clienteId);

    public abstract Credito1Response updateCredito1(Long id, LocalDate fechaInicial, LocalDate fechaFinal, Boolean TEoN,String TEP, String TNP,
                                                    Long tasa, Long perio_capitalizacion, Long clienteId);

    public abstract Credito1Response deleteCredito1(Long id);


}
