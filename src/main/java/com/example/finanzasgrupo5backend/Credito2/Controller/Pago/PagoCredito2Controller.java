package com.example.finanzasgrupo5backend.Credito2.Controller.Pago;

import com.example.finanzasgrupo5backend.Credito1.Model.ConsumoCredito1Response;
import com.example.finanzasgrupo5backend.Credito2.Model.Pago.PagoCredito2Response;
import com.example.finanzasgrupo5backend.Credito2.Service.ICredito2Service;
import com.example.finanzasgrupo5backend.Credito2.Service.Pago.IPagoCredito2Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "PagoCredito2 Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class PagoCredito2Controller {
    private final IPagoCredito2Service pagoCredito2Service;

    public PagoCredito2Controller(IPagoCredito2Service pagoCredito2Service) {
        this.pagoCredito2Service = pagoCredito2Service;
    }

    @Operation(summary = "Obtain a list of pagos by credito Id")
    @GetMapping("/pago-credito2/credito/{id}")
    public ResponseEntity<List<PagoCredito2Response>> getPagoCreditos2ByCreditoId(
            @PathVariable(name = "id") Long creditoId) {
        List<String[]> lista = pagoCredito2Service.getPagoCredito2ByCredito2(creditoId);

        List<PagoCredito2Response> listadto =new ArrayList<>();
        for (String[] strings : lista) {
            PagoCredito2Response pagoCredito2Response = new PagoCredito2Response();
            pagoCredito2Response.setId(Long.parseLong(strings[0]));
            pagoCredito2Response.setRenta(Double.parseDouble(strings[1]));
            pagoCredito2Response.setTotal_moras(Double.parseDouble(strings[2]));
            pagoCredito2Response.setTotal_monto_consumos(pagoCredito2Service.sumTotalProductosPago(creditoId, Long.parseLong(strings[0])));
            if (pagoCredito2Response.getTotal_monto_consumos() == null)
                pagoCredito2Response.setTotal_monto_consumos(0D);
            pagoCredito2Response.setMonto_a_pagar(Double.parseDouble(strings[3]));
            pagoCredito2Response.setCuota(Long.parseLong(strings[4]));
            pagoCredito2Response.setEstado(strings[5]);
            listadto.add(pagoCredito2Response);
        }

        var res = listadto;
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update Pago")
    @PatchMapping("/pago-credito2/{id}")
    public ResponseEntity<PagoCredito2Response> updatePagoCredito2(@PathVariable(name = "id") Long pagoId,
                                                                  @RequestParam String estado) {
        pagoCredito2Service.updatePagoCredito2(pagoId, estado);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
