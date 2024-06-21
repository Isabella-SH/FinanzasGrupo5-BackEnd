package com.example.finanzasgrupo5backend.Credito2.Controller.Consumo;

import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1Request;
import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1Response;
import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2Request;
import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2Response;
import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2ResponseHTTP;
import com.example.finanzasgrupo5backend.Credito2.Service.Consumo.IConsumoCredito2Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "ConsumoCredito2 Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class ConsumoCredito2Controller {
    @Autowired
    private IConsumoCredito2Service consumoCredito2Service;

    @Operation(summary = "Create a Consumo Credito2")
    @PostMapping("/consumos-credito2")
    public ResponseEntity<ConsumoCredito2ResponseHTTP> createConsumoCredito2
            (@RequestParam(name = "credito2Id") Long creditoId,
             @RequestParam(name = "productoId") Long productoId,
             @RequestBody ConsumoCredito2Request consumoCredito2Request) {

        var res = consumoCredito2Service.createConsumoCredito2(consumoCredito2Request, creditoId, productoId);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "List consumos by credito2 id")
    @GetMapping("/consumos-credito2/credito/listar/{id}")
    public ResponseEntity<List<ConsumoCredito2Response>> listConsumosByCredito(@PathVariable("id") Long creditoId) {
        List<String[]> lista = consumoCredito2Service.listConsumosByCredito(creditoId);

        List<ConsumoCredito2Response> listadto =new ArrayList<>();
        for (String[] strings : lista) {
            ConsumoCredito2Response consumo = new ConsumoCredito2Response();
            consumo.setId(Long.parseLong(strings[0]));
            consumo.setProductos(strings[1]);
            consumo.setPrecio(Double.parseDouble(strings[2]));
            consumo.setFechaInicial(strings[3]);
            consumo.setFechaFinal(strings[4]);
            consumo.setCuotas(Long.parseLong(strings[5]));
            consumo.setCreditos2(Long.parseLong(strings[6]));
            listadto.add(consumo);
        }
        return new ResponseEntity<>(listadto, HttpStatus.OK);
    }



}
