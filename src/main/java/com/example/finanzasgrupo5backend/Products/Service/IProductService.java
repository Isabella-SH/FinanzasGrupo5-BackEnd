package com.example.finanzasgrupo5backend.Products.Service;

import com.example.finanzasgrupo5backend.Products.Model.ProductRequest;
import com.example.finanzasgrupo5backend.Products.Model.ProductResponse;

import java.util.List;

public interface IProductService {

    public abstract List<ProductResponse> getAllProducts();

    public  abstract ProductResponse getProductsByStoreId(Long storeId);

    public abstract ProductResponse createProduct(ProductRequest product, Long storeId);

    public abstract ProductResponse updateProduct(Long id, ProductRequest product);

    public abstract ProductResponse deleteProduct(Long id);
}
