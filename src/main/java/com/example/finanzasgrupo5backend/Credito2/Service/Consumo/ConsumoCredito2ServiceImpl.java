package com.example.finanzasgrupo5backend.Credito2.Service.Consumo;

import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2;
import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2Request;
import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2Response;
import com.example.finanzasgrupo5backend.Credito2.Model.Consumo.ConsumoCredito2ResponseHTTP;
import com.example.finanzasgrupo5backend.Credito2.Repository.Consumo.IConsumoCredito2Repository;
import com.example.finanzasgrupo5backend.Credito2.Repository.ICredito2Repository;
import com.example.finanzasgrupo5backend.Formulas.Anualidades.SimpleVencida.AnualidadSimpleVencida;
import com.example.finanzasgrupo5backend.Products.Repository.IProductRepository;
import com.example.finanzasgrupo5backend.Shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.lang.Math.round;

@Service
@Qualifier("consumoCredito2ServiceImpl")
public class ConsumoCredito2ServiceImpl implements IConsumoCredito2Service {
    private final IConsumoCredito2Repository consumoCredito2Repository;
    private final ICredito2Repository credito2Repository;
    private final IProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ConsumoCredito2ServiceImpl(IConsumoCredito2Repository consumoCredito2Repository, ICredito2Repository credito2Repository, ModelMapper modelMapper,
                                      IProductRepository productRepository) {
        this.consumoCredito2Repository = consumoCredito2Repository;
        this.credito2Repository = credito2Repository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ConsumoCredito2ResponseHTTP createConsumoCredito2(ConsumoCredito2Request consumoCredito2Request, Long creditoId, Long productoId) {
        var existingCredito2 = credito2Repository.findById(creditoId)
            .orElseThrow(() -> new ResourceNotFoundException("No se encontró un credito2 con ID: " + creditoId));

        var existingProducts = productRepository.findById(productoId)
            .orElseThrow(() -> new ResourceNotFoundException("No se encontró un producto con ID: " + productoId));



        var newConsumo = modelMapper.map(consumoCredito2Request, ConsumoCredito2.class);
        LocalDate fechaInicialCredito = existingCredito2.getFechaInicial();
        LocalDate fechaFinalCredito = existingCredito2.getFechaFinal();
        Long cuotas = existingCredito2.getCuotas();
        Double daysCredito = AnualidadSimpleVencida.numeroDiasPorCuota(fechaInicialCredito,fechaFinalCredito,cuotas);
        Double dias_entre_fechas = (double) ChronoUnit.DAYS.between(fechaInicialCredito, newConsumo.getFechaInicial());

        //encuentra en que cuota se encuentra la fecha inicial del consumo en base a la cantidad de días que han pasado



        //encontrar en que cuota se encuentra la fecha incial del consumo dentro de las cuotas divididas por el total de dias del credito
        Long cuotaActual = 0l;
        for (long i = 1; i <= cuotas; i++) {
            if (dias_entre_fechas <= daysCredito * i) {
                cuotaActual = i;
                break;
            }
        }
        System.out.println(daysCredito);
        System.out.println(dias_entre_fechas);
        System.out.println(cuotaActual);


        //sumar fecha incial de credito mas el numero de cuota por el total de dias de la cuota para hallar la fecha final de cada cuota
        LocalDate fechaFinalConsumo = fechaInicialCredito.plusDays(round(daysCredito * cuotaActual));
        newConsumo.setFechaFinal(fechaFinalConsumo);
        newConsumo.setCredito2(existingCredito2);
        newConsumo.setCuota(cuotaActual);
        newConsumo.setProducto(existingProducts);

        var savedConsumo = consumoCredito2Repository.save(newConsumo);
        var response = modelMapper.map(savedConsumo, ConsumoCredito2ResponseHTTP.class);

        return response;
    }

    @Override
    public ConsumoCredito2Response updateConsumoCredito2() {
        return null;
    }

    @Override
    public ConsumoCredito2Response deleteConsumoCredito2(Long id) {
        // Buscar el consumo
        var consumo = consumoCredito2Repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un consumo con el id: " + id));

        // Eliminar el producto
        consumoCredito2Repository.delete(consumo);

        // Mapeo de la respuesta para confirmar eliminación
        var response = modelMapper.map(consumo, ConsumoCredito2Response.class);

        return response;
    }

    @Override
    public List<String[]> listConsumosByCredito(Long creditoId) {
        return consumoCredito2Repository.listConsumosByCredito(creditoId);
    }
}
