package com.example.finanzasgrupo5backend.Credito1.Controller;


import com.example.finanzasgrupo5backend.Credito1.Model.Credito1Request;
import com.example.finanzasgrupo5backend.Credito1.Model.Credito1Response;
import com.example.finanzasgrupo5backend.Credito1.Service.ICredito1Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Credito1 Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class Credito1Controller {
    private final ICredito1Service credito1Service;

    public Credito1Controller(ICredito1Service credito1Service){
        this.credito1Service=credito1Service;
    }

    @Operation(summary = "Create a Credito1")
    @PostMapping("/credito1")
    public ResponseEntity<Credito1Response> createCredito1
            (@RequestParam(name = "clienteId") Long clienteId, @RequestBody Credito1Request credito1Request) {

        var res = credito1Service.createCredito1(credito1Request, clienteId);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtain a list of all creditos")
    @GetMapping("/credito1")
    public ResponseEntity<List<Credito1Response>> getAllCreditos1() {
        var res = credito1Service.getAllCredito1();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Obtain a list of creditos by cliente Id")
    @GetMapping("/credito1/cliente/{id}")
    public ResponseEntity<List<Credito1Response>> getCreditos1ByClienteId(@PathVariable(name = "id") Long clienteId) {
        var res = credito1Service.getCreditos1ByClienteId(clienteId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update a c")
    @PatchMapping("/clienteId/{id}")
    public ResponseEntity<Credito1Response> updateCredito1(@PathVariable(name = "id") Long creditoId,
                                                           @RequestParam LocalDate fechaInicial, @RequestParam LocalDate fechaFinal, @RequestParam Boolean TEoN,@RequestParam String TEP, @RequestParam String TNP,
                                                           @RequestParam Long tasa, @RequestParam Long perio_capitalizacion) {

        long clienteId=1;
        var res = credito1Service.updateCredito1(creditoId, fechaInicial, fechaFinal, TEoN,TEP, TNP,
                tasa, perio_capitalizacion,clienteId);
        if (res == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }
    @Operation(summary = "Delete a credito1")
    @DeleteMapping("/credito1/{id}")
    public ResponseEntity<Void> deleteCredito1(@PathVariable(name = "id") Long credito1Id) {
        credito1Service.deleteCredito1(credito1Id);
        return ResponseEntity.noContent().build();
    }
}
