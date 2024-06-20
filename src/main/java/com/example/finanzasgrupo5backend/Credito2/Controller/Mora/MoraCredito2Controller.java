package com.example.finanzasgrupo5backend.Credito2.Controller.Mora;

import com.example.finanzasgrupo5backend.Credito2.Model.Mora.MoraCredito2Request;
import com.example.finanzasgrupo5backend.Credito2.Model.Mora.MoraCredito2Response;
import com.example.finanzasgrupo5backend.Credito2.Service.Mora.IMoraCredito2Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Mora Credito2 Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class MoraCredito2Controller {

    private final IMoraCredito2Service moraCredito2Service;

    public MoraCredito2Controller(IMoraCredito2Service moraCredito2Service) {
        this.moraCredito2Service = moraCredito2Service;
    }

    @Operation(summary = "Create a MoraCredito2")
    @PostMapping("/mora-credito2")
    public ResponseEntity<MoraCredito2Response> createMoraCredito2
            (@RequestParam(name = "credito2Id") Long credito2Id, @RequestBody MoraCredito2Request moraCredito2Request) {


        var res = moraCredito2Service.createMoraCredito2(moraCredito2Request,credito2Id);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtain a list of all moras credito 2")
    @GetMapping("/mora-credito2")
    public ResponseEntity<List<MoraCredito2Response>> getAllMoraCreditos2() {
        var res = moraCredito2Service.getAllMoraCredito2();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Obtain a list of moras credito2 by credito2 Id")
    @GetMapping("/moras-credito2/credito2/{id}")
    public ResponseEntity<List<MoraCredito2Response>> getMoraCreditos2ByCredito2Id(@PathVariable(name = "id") Long credito2Id) {
        var res = moraCredito2Service.getMoraCredito2ByCreditoId(credito2Id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update a mora-credito2")
    @PatchMapping("/moras-credito2/{id}")
    public ResponseEntity<MoraCredito2Response> updateMoraCredito2(@PathVariable(name = "id") Long id,
                                                                   @RequestParam String TEPm, @RequestParam Double tasa, @RequestParam Double dias_atraso){

        var res = moraCredito2Service.updateMoraCredito2(id, TEPm, tasa, dias_atraso);
        if (res == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Delete a mora credito1")
    @DeleteMapping("/mora-credito2/{id}")
    public ResponseEntity<Void> deleteMoraCredito2(@PathVariable(name = "id") Long credito2Id) {
        moraCredito2Service.deleteMoraCredito2(credito2Id);
        return ResponseEntity.noContent().build();
    }
}










