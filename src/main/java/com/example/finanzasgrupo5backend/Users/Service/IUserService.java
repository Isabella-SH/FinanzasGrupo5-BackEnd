package com.example.finanzasgrupo5backend.Users.Service;

import com.example.finanzasgrupo5backend.Users.Model.Users;

import java.util.List;

public interface IUserService {
    public void insertUser(Users users);
    public List<Users> listUsers();
}