package com.example.finanzasgrupo5backend.Credito2.Controller.Pago;

import com.example.finanzasgrupo5backend.Credito2.Service.ICredito2Service;
import com.example.finanzasgrupo5backend.Credito2.Service.Pago.IPagoCredito2Service;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PagoCredito2 Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class PagoCredito2Controller {
    private final IPagoCredito2Service pagoCredito2Service;

    public PagoCredito2Controller(IPagoCredito2Service pagoCredito2Service) {
        this.pagoCredito2Service = pagoCredito2Service;
    }




}
