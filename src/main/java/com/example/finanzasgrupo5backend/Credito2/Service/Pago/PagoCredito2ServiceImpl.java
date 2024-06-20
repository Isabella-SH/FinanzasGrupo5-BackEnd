package com.example.finanzasgrupo5backend.Credito2.Service.Pago;

import com.example.finanzasgrupo5backend.Credito1.Model.PagoCredito1;
import com.example.finanzasgrupo5backend.Credito1.Model.PagoCredito1Response;
import com.example.finanzasgrupo5backend.Credito2.Model.Pago.PagoCredito2;
import com.example.finanzasgrupo5backend.Credito2.Repository.ICredito2Repository;
import com.example.finanzasgrupo5backend.Credito2.Repository.Mora.IMoraCredito2Repository;
import com.example.finanzasgrupo5backend.Credito2.Repository.Pago.IPagoCredito2Repository;
import com.example.finanzasgrupo5backend.Shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoCredito2ServiceImpl implements IPagoCredito2Service {

    @Autowired
    private IMoraCredito2Repository moraCredito2Repository;

    @Autowired
    private ICredito2Repository credito2Repository;

    @Autowired
    private IPagoCredito2Repository pagoCredito2Repository;

    @Override
    public void createPagoCredito2(Double renta, Double total_moras, Long cuota, String estado, Long id_credito2) {
        var existingCredito2 = credito2Repository.findById(id_credito2)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un credito1 con ID: " + id_credito2));

        var existingMoraCredito2 = moraCredito2Repository.findMoraByCredito2Id(id_credito2);

        // Mapeo
        var newPago = new PagoCredito2();

        newPago.setCredito2(existingCredito2); //asocia el pago a un credito2
        newPago.setRenta(renta);
        newPago.setTotal_moras(total_moras);
        newPago.setMonto_a_pagar(renta + total_moras);
        newPago.setCuota(cuota);
        newPago.setEstado(estado);

        pagoCredito2Repository.save(newPago);

    }

    @Override
public void updatePagoCredito2(Long id, Double total_moras,Long cuota, String estado, Long id_credito2) {
        var existingPago = pagoCredito2Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un pago con ID: " + id));

        var existingCredito2 = credito2Repository.findById(id_credito2)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un credito2 con ID: " + id_credito2));

        existingPago.setCredito2(existingCredito2); //asocia el pago a un credito2
        existingPago.setTotal_moras(total_moras);
        existingPago.setCuota(cuota);
        existingPago.setEstado(estado);
        existingPago.setMonto_a_pagar(existingPago.getRenta() + total_moras);

        pagoCredito2Repository.save(existingPago);
    }


}
