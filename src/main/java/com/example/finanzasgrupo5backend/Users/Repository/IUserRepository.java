package com.example.finanzasgrupo5backend.Users.Repository;

import com.example.finanzasgrupo5backend.Users.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<Users,Long> {

}
