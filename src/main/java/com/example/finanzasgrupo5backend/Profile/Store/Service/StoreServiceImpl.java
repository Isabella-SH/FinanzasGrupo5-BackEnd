package com.example.finanzasgrupo5backend.Profile.Store.Service;

import com.example.finanzasgrupo5backend.Profile.Store.Model.Store;
import com.example.finanzasgrupo5backend.Profile.Store.Model.StoreRequest;
import com.example.finanzasgrupo5backend.Profile.Store.Model.StoreResponse;
import com.example.finanzasgrupo5backend.Profile.Store.Repository.IStoreRepository;
import com.example.finanzasgrupo5backend.Shared.exception.ResourceNotFoundException;
import com.example.finanzasgrupo5backend.Users.Repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        newStore.setUser(existingUser); //asocia el producto a un store

        var createStore = storeRepository.save(newStore);
        var response = modelMapper.map(createStore, StoreResponse.class);

        return response;



    }

    @Override
    public List<StoreResponse> getAllStores() {
        var existingStore = storeRepository.findAll();
        if (existingStore.isEmpty())
            throw new ResourceNotFoundException("No existe ningun producto");

        var toShowStore = existingStore.stream()
                .map(Store -> modelMapper.map(Store, StoreResponse.class))
                .toList();

        return toShowStore;
    }

    @Override
    public List<StoreResponse> getStoreByUserId(Long user_id) {
        // Buscar si existe el User
        var existingUser = userRepository.findById(user_id);
        if (existingUser.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el usuario con id " + user_id);
        }

        //obtine los productos asociados al usuarioid
        var existingStore = storeRepository.findStoreByUserId(user_id);
        if (existingStore.isEmpty())
            throw new ResourceNotFoundException("No existe ningun store para el usuario con id "+user_id);

        // Muestra la lista de store asociados al usuario
        var toShowStore = existingStore.stream()
                .map(Store -> modelMapper.map(Store, StoreResponse.class))
                .toList();

        return toShowStore;
    }

    @Override
    public StoreResponse updateStore(Long id, String name, Long user_id) {
        // Buscar el store
        var store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro un store con el id: " + id));


        store.setName(name);


        // Guardar el store actualizado
        var updatedreservation = storeRepository.save(store);

        // Retornar la respuesta actualizada
        return modelMapper.map(updatedreservation, StoreResponse.class);
    }

    @Override
    public StoreResponse deleteStore(Long id) {
        // Buscar el store
        var store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un store con el id: " + id));

        // Eliminar el producto
        storeRepository.delete(store);

        // Mapeo de la respuesta para confirmar eliminación
        var response = modelMapper.map(store, StoreResponse.class);

        return response;
    }




}
