package com.example.finanzasgrupo5backend.Users.Service;

import com.example.finanzasgrupo5backend.Users.Entity.Users;
import com.example.finanzasgrupo5backend.Users.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements IUserService {

    IUserRepository userRepository;
    @Override
    public void insertUser(Users users) {
        userRepository.save(users);
    }

    @Override
    public List<Users> listUsers() {
        return null;
    }
}
