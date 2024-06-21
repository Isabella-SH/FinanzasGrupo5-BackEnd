package com.example.finanzasgrupo5backend.Credito2.Service.Pago;

import com.example.finanzasgrupo5backend.Credito1.Model.PagoCredito1;
import com.example.finanzasgrupo5backend.Credito1.Model.PagoCredito1Response;
import com.example.finanzasgrupo5backend.Credito2.Model.Credito.Credito2;
import com.example.finanzasgrupo5backend.Credito2.Model.Pago.PagoCredito2;
import com.example.finanzasgrupo5backend.Credito2.Model.Pago.PagoCredito2Response;
import com.example.finanzasgrupo5backend.Credito2.Repository.ICredito2Repository;
import com.example.finanzasgrupo5backend.Credito2.Repository.Mora.IMoraCredito2Repository;
import com.example.finanzasgrupo5backend.Credito2.Repository.Pago.IPagoCredito2Repository;
import com.example.finanzasgrupo5backend.Shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoCredito2ServiceImpl implements IPagoCredito2Service {

    @Autowired
    private IMoraCredito2Repository moraCredito2Repository;

    @Autowired
    private ICredito2Repository credito2Repository;

    @Autowired
    private IPagoCredito2Repository pagoCredito2Repository;
    private ModelMapper modelMapper;

    public PagoCredito2ServiceImpl(ModelMapper modelMapper) {

        this.modelMapper = modelMapper;
    }

    @Override
    public void createPagoCredito2(Double renta, Double total_moras, Long cuota, String estado, Long id_credito2) {
        var existingCredito2 = credito2Repository.findById(id_credito2)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un credito1 con ID: " + id_credito2));

        var existingMoraCredito2 = moraCredito2Repository.findMoraByCredito2Id(id_credito2);

        if (existingMoraCredito2.isEmpty())
            total_moras = 0D;

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
public void updatePagoCredito2(Long id, String estado) {
        var existingPago = pagoCredito2Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un pago con ID: " + id));

        var existingCredito2 = credito2Repository.findById(existingPago.getCredito2().getId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un credito2 con ID: " + existingPago.getCredito2().getId()));

        var existingMoraCredito2 = moraCredito2Repository.findMoraByCredito2Id(existingPago.getCredito2().getId());

        if (!existingMoraCredito2.isEmpty())
            existingPago.setTotal_moras(existingMoraCredito2.stream().map(mora -> mora.getTotal_moras()).reduce(0D, Double::sum));
        else
            existingPago.setTotal_moras(0D);

        existingPago.setCredito2(existingCredito2); //asocia el pago a un credito2
        existingPago.setCuota(existingPago.getCuota());
        existingPago.setEstado(estado);
        existingPago.setMonto_a_pagar(existingPago.getRenta() + existingPago.getTotal_moras());

        pagoCredito2Repository.save(existingPago);
    }


    @Override
    public List<String[]> getPagoCredito2ByCredito2(Long creditoId) {
        System.out.println("holla");
        return pagoCredito2Repository.getPagoCredito2(creditoId);
    }

    @Override
    public Double sumTotalProductosPago(Long creditoId, Long pago) {
        return pagoCredito2Repository.sumTotalProductosPago(creditoId, pago);
    }
}
