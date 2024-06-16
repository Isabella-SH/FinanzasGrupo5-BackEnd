package com.example.finanzasgrupo5backend.Profile.Store.Service;

import com.example.finanzasgrupo5backend.Profile.Clients.Model.Client;
import com.example.finanzasgrupo5backend.Profile.Clients.Model.ClientResponse;
import com.example.finanzasgrupo5backend.Profile.Store.Model.Store;
import com.example.finanzasgrupo5backend.Profile.Store.Model.StoreRequest;
import com.example.finanzasgrupo5backend.Profile.Store.Model.StoreResponse;
import com.example.finanzasgrupo5backend.Profile.Store.Repository.IStoreRepository;
import com.example.finanzasgrupo5backend.Shared.exception.ResourceNotFoundException;
import com.example.finanzasgrupo5backend.Users.Repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements IStoreService{

    @Autowired
    private IStoreRepository storeRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StoreResponse createStore(Long user_id, StoreRequest storeRequest){
        var existingUser  = userRepository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el un user con ID: " + user_id));

        var newStore = modelMapper.map(storeRequest, Store.class);

        newStore.setUser_id(existingUser); //asocia el producto a un store

        var createStore = storeRepository.save(newStore);
        var response = modelMapper.map(createStore, StoreResponse.class);

        return response;



    }

    @Override
    public void deleteStore(Long id_store) {
        storeRepository.deleteById(id_store);
    }


}
