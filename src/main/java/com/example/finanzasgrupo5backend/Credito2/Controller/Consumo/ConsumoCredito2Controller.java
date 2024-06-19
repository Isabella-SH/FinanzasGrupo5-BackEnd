package com.example.finanzasgrupo5backend.Credito2.Controller.Consumo;

import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1Request;
import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1Response;
import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2Request;
import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2Response;
import com.example.finanzasgrupo5backend.Credito2.Service.Consumo.IConsumoCredito2Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ConsumoCredito2 Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class ConsumoCredito2Controller {
    @Autowired
    private IConsumoCredito2Service consumoCredito2Service;

    @Operation(summary = "Create a Consumo Credito2")
    @PostMapping("/consumos-credito2")
    public ResponseEntity<ConsumoCredito2Response> createConsumoCredito2
            (@RequestParam(name = "credito2Id") Long creditoId, @RequestBody ConsumoCredito2Request consumoCredito2Request) {

        var res = consumoCredito2Service.createConsumoCredito2(consumoCredito2Request, creditoId);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }



}
