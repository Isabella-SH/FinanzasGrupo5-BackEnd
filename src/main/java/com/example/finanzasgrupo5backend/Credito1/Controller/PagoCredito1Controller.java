package com.example.finanzasgrupo5backend.Credito1.Controller;

import com.example.finanzasgrupo5backend.Credito1.Model.*;

import com.example.finanzasgrupo5backend.Credito1.Service.IMoraCredito1Service;
import com.example.finanzasgrupo5backend.Credito1.Service.IPagoCredito1Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Tag(name = "Pago-Credito1 Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class PagoCredito1Controller {
    private final IPagoCredito1Service pagoCredito1Service;

    public PagoCredito1Controller(IPagoCredito1Service pagoCredito1Service) {
        this.pagoCredito1Service = pagoCredito1Service;
    }

    @Operation(summary = "Create a Pago-Credito1")
    @PostMapping("/pago-credito1")
    public ResponseEntity<PagoCredito1Response> createCredito1
            (@RequestParam(name = "credito1Id") Long creditoId) {

        var res = pagoCredito1Service.createPagoCredito1(creditoId);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtain a list of all pagos")
    @GetMapping("/pago-credito1")
    public ResponseEntity<List<PagoCredito1Response>> getAllPagoCreditos1() {
        var res = pagoCredito1Service.getAllPagoCredito1();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Obtain a list of pagos by credito Id")
    @GetMapping("/pago-credito1/credito/{id}")
    public ResponseEntity<List<PagoCredito1Response>> getPagoCreditos1ByCreditoId(@PathVariable(name = "id") Long creditoId) {
        var res = pagoCredito1Service.getPagoCredito1ByCreditoId(creditoId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update a pago-credito1")
    @PatchMapping("/pago-credito1/{id}")
    public ResponseEntity<PagoCredito1Response> updatePagoCredito1(@PathVariable(name = "id") Long pagoId,
                                                           @RequestParam Double total_moras, @RequestParam Double total_monto_consumos, @RequestParam Double monto_a_pagar, @RequestParam Boolean pagado) {

        long creditoId=1;
        long moraId=1;

        var res = pagoCredito1Service.updatePagoCredito1(pagoId, total_moras,total_monto_consumos,monto_a_pagar,pagado,creditoId,moraId);
        if (res == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }


    @Operation(summary = "Delete a pago-credito1")
    @DeleteMapping("/pago-credito1/{id}")
    public ResponseEntity<Void> deletePagoCredito1(@PathVariable(name = "id") Long credito1Id) {
        pagoCredito1Service.deletePagoCredito1(credito1Id);
        return ResponseEntity.noContent().build();
    }



}
