package com.example.finanzasgrupo5backend.Profile.Store.Service;

import com.example.finanzasgrupo5backend.Profile.Store.Model.Store;
import com.example.finanzasgrupo5backend.Profile.Store.Model.StoreRequest;
import com.example.finanzasgrupo5backend.Profile.Store.Model.StoreResponse;

public interface IStoreService {
    public abstract StoreResponse createStore(Long user_id, StoreRequest storeRequest);


    public abstract void deleteStore(Long id_store);


}
