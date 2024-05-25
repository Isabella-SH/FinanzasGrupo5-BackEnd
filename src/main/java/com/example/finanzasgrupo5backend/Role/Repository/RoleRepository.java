package com.example.finanzasgrupo5backend.Role.Repository;

import com.example.finanzasgrupo5backend.Role.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}