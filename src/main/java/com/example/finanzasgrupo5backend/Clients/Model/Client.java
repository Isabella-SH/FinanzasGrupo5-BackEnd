package com.example.finanzasgrupo5backend.Clients.Model;
import com.example.finanzasgrupo5backend.Credito1.Model.Credito1;
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

    @Column(name = "credit_limit", nullable = false)
    private Long credit_limit;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Credito1> credito1s;
}
