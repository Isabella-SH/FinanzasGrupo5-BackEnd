package com.example.finanzasgrupo5backend.Credito1.Controller;

import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1Request;
import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1Response;
import com.example.finanzasgrupo5backend.Credito1.Service.IConsumoCredito1Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Consumo Credito1 Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class ConsumoCredito1Controller {

    private final IConsumoCredito1Service consumoCredito1Service;

    public ConsumoCredito1Controller( IConsumoCredito1Service consumoCredito1Service){
        this.consumoCredito1Service =consumoCredito1Service;
    }

    @Operation(summary = "Create a Consumo Credito1")
    @PostMapping("/consumos-credito1")
    public ResponseEntity<ConsumoCredito1Response> createConsumoCredito1
            (@RequestParam(name = "credito1Id") Long creditoId, @RequestBody ConsumoCredito1Request consumoCredito1Request) {

        var res = consumoCredito1Service.createConsumo(consumoCredito1Request, creditoId);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtain a list of all consumos credito1")
    @GetMapping("/consumos-credito1")
    public ResponseEntity<List<ConsumoCredito1Response>> getAllConsumosCredito1() {
        var res = consumoCredito1Service.getAllConsumosCredito1();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Obtain a list of consumos credito1 by credito Id")
    @GetMapping("/consumos-credito1/credito1/{id}")
    public ResponseEntity<List<ConsumoCredito1Response>> getConsumosCredito1ByCredito1Id(@PathVariable(name = "id") Long credito1Id) {
        var res = consumoCredito1Service.getConsumosByCredito1Id(credito1Id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Delete a consumo credito1")
    @DeleteMapping("/consumos-credito1/{id}")
    public ResponseEntity<Void> deleteConsumoCredito1(@PathVariable(name = "id") Long credito1Id) {
        consumoCredito1Service.deleteConsumo(credito1Id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Sum consumos by credito id")
    @GetMapping("/consumos-credito1/credito/{id}")
    public ResponseEntity<Long> sumTotalConsumoByCreditoId(@PathVariable("id") Long creditoId) {
        var res = consumoCredito1Service.sumTotalConsumoByCreditoId(creditoId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
