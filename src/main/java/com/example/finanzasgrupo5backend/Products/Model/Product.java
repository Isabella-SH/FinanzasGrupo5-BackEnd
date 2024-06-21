package com.example.finanzasgrupo5backend.Products.Model;

import com.example.finanzasgrupo5backend.Profile.Store.Model.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    //muchos productos pertenecen a un negocio
    @ManyToOne
    @JoinColumn(name = "store", nullable = false)
    private Store store;

}
