package com.example.finanzasgrupo5backend.Profile.Clients.Model;
import com.example.finanzasgrupo5backend.Credito1.Model.Credito1;
import com.example.finanzasgrupo5backend.Profile.Store.Model.Store;
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
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dni", nullable = false)
    private Long dni;

    @Column(name = "email", nullable = false)
    private String email;


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Credito1> credito1s;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user_id;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store_id;
}
