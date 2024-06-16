package com.example.finanzasgrupo5backend.Credito1.Controller;

import com.example.finanzasgrupo5backend.Credito1.Model.*;
import com.example.finanzasgrupo5backend.Credito1.Service.IMoraCredito1Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@Tag(name = "Credito1 Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class MoraCredito1Controller {

    private final IMoraCredito1Service moraCredito1Service;

    public MoraCredito1Controller(IMoraCredito1Service moraCredito1Service) {
        this.moraCredito1Service = moraCredito1Service;
    }

    @Operation(summary = "Create a MoraCredito1")
    @PostMapping("/mora-credito1")
    public ResponseEntity<MoraCredito1Response> createMoraCredito1
            (@RequestParam(name = "credito1Id") Long creditoId, @RequestBody MoraCredito1Request moraCredito1Request, @RequestBody ConsumoCredito1Request consumoCredito1Request, @RequestBody ConsumoCredito1 consumoCredito1) {


        var res = moraCredito1Service.createMoraCredito1(consumoCredito1, consumoCredito1Request, moraCredito1Request,creditoId);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    /*
    @Operation(summary = "Sum consumos by credito id")
    @GetMapping("/consumos-credito1/credito/{id}")
    public ResponseEntity<Long> sumTotalConsumoByCreditoId(@PathVariable("id") Long creditoId) {
        var res = moraCredito1Service.sumTotalConsumoByCreditoId(creditoId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    */

    @Operation(summary = "Obtain a list of all moras credito 1")
    @GetMapping("/mora-credito1")
    public ResponseEntity<List<MoraCredito1Response>> getAllMoraCreditos1() {
        var res = moraCredito1Service.getAllMoraCredito1();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Obtain a list of moras credito1 by credito Id")
    @GetMapping("/moras-credito1/credito1/{id}")
    public ResponseEntity<List<MoraCredito1Response>> getMoraCreditos1ByCredito1Id(@PathVariable(name = "id") Long creditoId) {
        var res = moraCredito1Service.getMoraCredito1ByCreditoId(creditoId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update a mora-credito1")
    @PatchMapping("/moras-credito1/{id}")
    public ResponseEntity<MoraCredito1Response> updateMoraCredito1(@PathVariable(name = "id") Long moraCredito1Id,
                                                           @RequestParam String TEPm, @RequestParam Long tasa, @RequestParam Long dias_atraso, @RequestParam Long total_moras){


        long credito1Id=1;
        var res = moraCredito1Service.updateMoraCredito1(moraCredito1Id, TEPm, tasa, dias_atraso,total_moras, credito1Id);
        if (res == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }
    @Operation(summary = "Delete a mora credito1")
    @DeleteMapping("/mora-credito1/{id}")
    public ResponseEntity<Void> deleteMoraCredito1(@PathVariable(name = "id") Long credito1Id) {
        moraCredito1Service.deleteMoraCredito1(credito1Id);
        return ResponseEntity.noContent().build();
    }
}
