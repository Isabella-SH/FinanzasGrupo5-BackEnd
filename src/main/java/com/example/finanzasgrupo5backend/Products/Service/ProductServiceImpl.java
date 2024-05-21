package com.example.finanzasgrupo5backend.Products.Service;

import com.example.finanzasgrupo5backend.Products.Model.ProductRequest;
import com.example.finanzasgrupo5backend.Products.Model.ProductResponse;
import com.example.finanzasgrupo5backend.Products.Repository.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@Qualifier("productServiceImpl")
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(IProductRepository productRepository, ModelMapper modelMapper){
        this.modelMapper=modelMapper;
        this.productRepository=productRepository;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return null;
    }

    @Override
    public ProductResponse getProductsByStoreId(Long storeId) {
        return null;
    }


    //POST
    @Override
    public ProductResponse createProduct(ProductRequest product, Long storeId) {

        // Buscar el negocio
        //var store = customerRepository.findById(customerId)
        //        .orElseThrow(() -> new ResourceNotFoundException("No se encontró el cliente con ID: " + customerId));

        // Validación


        return null;
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest product) {
        return null;
    }

    @Override
    public ProductResponse deleteProduct(Long id) {
        return null;
    }
}
