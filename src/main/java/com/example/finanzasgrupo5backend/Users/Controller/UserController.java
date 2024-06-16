package com.example.finanzasgrupo5backend.Users.Controller;

import com.example.finanzasgrupo5backend.Users.Entity.Users;
import com.example.finanzasgrupo5backend.Users.Model.UsersResponse;
import com.example.finanzasgrupo5backend.Users.Service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private IUserService uS;

    @PostMapping("/users")
    public void registrar(@RequestBody UsersResponse dto){
        ModelMapper m = new ModelMapper();
        Users u = m.map(dto,Users.class);
        uS.insertUser(u);
    }

    @PostMapping("/insert_role")
    public void insertarRol(@RequestParam("authority") String authority, @RequestParam("user_id") Long user_id){
        uS.insRol(authority,user_id);
    }

}
