package com.example.finanzasgrupo5backend.Products.Service;

import com.example.finanzasgrupo5backend.Products.Model.Product;
import com.example.finanzasgrupo5backend.Products.Model.ProductRequest;
import com.example.finanzasgrupo5backend.Products.Model.ProductResponse;
import com.example.finanzasgrupo5backend.Products.Repository.IProductRepository;
import com.example.finanzasgrupo5backend.Profile.Store.Repository.IStoreRepository;
import com.example.finanzasgrupo5backend.Shared.exception.ResourceNotFoundException;
import com.example.finanzasgrupo5backend.Shared.exception.ValidationException;
import com.example.finanzasgrupo5backend.Validations.ProductValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("productServiceImpl")
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final IStoreRepository storeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(IProductRepository productRepository, IStoreRepository storeRepository,ModelMapper modelMapper){
        this.modelMapper=modelMapper;
        this.productRepository=productRepository;
        this.storeRepository=storeRepository;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        var existingProducts = productRepository.findAll();
        if (existingProducts.isEmpty())
            throw new ResourceNotFoundException("No existe ningun producto");

        var toShowProducts = existingProducts.stream()
                .map(Product -> modelMapper.map(Product, ProductResponse.class))
                .toList();

        return toShowProducts;
    }

    @Override
    public List<ProductResponse> getProductsByStoreId(Long storeId){

        // Buscar si existe el store
        var existingStore = storeRepository.findById(storeId);
        if (existingStore.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el store con id " + storeId);
        }

        //obtine los productos asociados al storeid
        var existingProducts = productRepository.findProductByStoreId(storeId);
        if (existingProducts.isEmpty())
            throw new ResourceNotFoundException("No existe ningun producto para el store con id "+storeId);

        // Muestra la lista de productos asociados al store
        var toShowProducts = existingProducts.stream()
                .map(Product -> modelMapper.map(Product, ProductResponse.class))
                .toList();

        return toShowProducts;
    }


    //POST
    @Override
    public ProductResponse createProduct(ProductRequest productRequest, Long storeId) {

        // Buscar el negocioalq ue pertenece
        var existingStore = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el un store con ID: " + storeId));

        // Validación
        ProductValidation.ValidateProduct(productRequest);

        // Mapeo
        var newProduct = modelMapper.map(productRequest, Product.class);

        newProduct.setStore(existingStore); //asocia el producto a un store

        var createProduct = productRepository.save(newProduct);
        var response = modelMapper.map(createProduct, ProductResponse.class);

        return response;
    }

    @Override
    public ProductResponse updateProduct(Long id, String name, Long price, Long storeId) {

        // Buscar el producto
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro un producto con el id: " + id));

        // Validación
        if (price <= 0) throw new ValidationException("El precio debe ser mayor a 0");

        product.setPrice(price);
        product.setName(name);

        // Guardar el producto actualizado
        var updatedreservation = productRepository.save(product);

        // Retornar la respuesta actualizada
        return modelMapper.map(updatedreservation, ProductResponse.class);

    }

    @Override
    public ProductResponse deleteProduct(Long id) {
        // Buscar el producto
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un producto con el id: " + id));

        // Eliminar el producto
        productRepository.delete(product);

        // Mapeo de la respuesta para confirmar eliminación
        var response = modelMapper.map(product, ProductResponse.class);

        return response;
    }

}
