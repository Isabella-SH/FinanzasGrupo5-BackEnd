package com.example.finanzasgrupo5backend.Profile.Store.Service;

import com.example.finanzasgrupo5backend.Profile.Store.Model.StoreRequest;
import com.example.finanzasgrupo5backend.Profile.Store.Model.StoreResponse;

import java.util.List;

public interface IStoreService {

    StoreResponse createStore(Long user_id, StoreRequest storeRequest);
   List<StoreResponse> getAllStores();

    List<StoreResponse>  getStoreByUserId(Long user_id);

    StoreResponse updateStore(Long id, String name, Long user_id);

    StoreResponse deleteStore(Long id);


}
