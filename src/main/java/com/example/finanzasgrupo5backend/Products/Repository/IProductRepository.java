package com.example.finanzasgrupo5backend.Products.Repository;

import com.example.finanzasgrupo5backend.Products.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByStoreId(Long storeId);
}
