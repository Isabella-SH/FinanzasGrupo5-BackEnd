package com.example.finanzasgrupo5backend.Profile.Store.Model;

import com.example.finanzasgrupo5backend.Products.Model.Product;
import com.example.finanzasgrupo5backend.Users.Entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    //un negocio tiene muchos productos
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Product> products;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

}
