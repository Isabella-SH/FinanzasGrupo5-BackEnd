package com.example.finanzasgrupo5backend.Products.Model;

import com.example.finanzasgrupo5backend.Profile.Model.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long price;

    //muchos productos pertenecen a un negocio
    @ManyToOne
    @JoinColumn(name = "store", nullable = false)
    private Store store;

}
