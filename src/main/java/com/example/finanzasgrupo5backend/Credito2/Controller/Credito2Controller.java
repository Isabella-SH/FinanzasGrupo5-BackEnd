package com.example.finanzasgrupo5backend.Credito2.Controller;



import com.example.finanzasgrupo5backend.Credito2.Model.Credito.Credito2Request;
import com.example.finanzasgrupo5backend.Credito2.Model.Credito.Credito2Response;
import com.example.finanzasgrupo5backend.Credito2.Service.ICredito2Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Credito2 Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class Credito2Controller {

    private final ICredito2Service credito2Service;

    public Credito2Controller(ICredito2Service credito2Service) {
        this.credito2Service = credito2Service;
    }

    @Operation(summary = "Create a Credito2")
    @PostMapping("/credito2")
    public ResponseEntity<Credito2Response> createCredito2
            (@RequestParam(name = "clienteId") Long clienteId, @RequestBody Credito2Request credito2Request) {

        var res = credito2Service.createCreditos2(credito2Request, clienteId);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtain a list of all creditos 2")
    @GetMapping("/credito2")
    public ResponseEntity<List<Credito2Response>> getAllCreditos1() {
        var res = credito2Service.getAllCreditos2();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Obtain a list of creditos by cliente Id")
    @GetMapping("/credito2/cliente/{id}")
    public ResponseEntity<List<Credito2Response>> getCreditos2ByClienteId(@PathVariable(name = "id") Long clienteId) {
        var res = credito2Service.getCreditos2ByClienteId(clienteId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update a credito 2")
    @PatchMapping("/credito2/clienteId/{id}")
    public ResponseEntity<Credito2Response> updateCredito2(@PathVariable(name = "id") Long creditoId,
                                                           @RequestParam LocalDate fechaInicial, @RequestParam LocalDate fechaFinal,  @RequestParam String TEP,
                                                           @RequestParam Double tasa, @RequestParam Double renta, @RequestParam Long cuotas, @RequestParam Long dias_plazo_gracia) {

        long clienteId=1;
        var res = credito2Service.updateCredito2(creditoId, fechaInicial, fechaFinal, TEP,
                tasa, renta, cuotas, dias_plazo_gracia, Long.valueOf(clienteId));
        if (res == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Delete a credito2")
    @DeleteMapping("/credito2/{id}")
    public ResponseEntity<Void> deleteCredito2(@PathVariable(name = "id") Long credito1Id) {
        credito2Service.deleteCredito2(credito1Id);
        return ResponseEntity.noContent().build();
    }





}
