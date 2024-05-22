package com.example.finanzasgrupo5backend.Products.Repository;

import com.example.finanzasgrupo5backend.Products.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {

    List<Product> findProductByStoreId(Long storeId);
    List<Product> findAll();

    List<Product> findById(long id);

}
