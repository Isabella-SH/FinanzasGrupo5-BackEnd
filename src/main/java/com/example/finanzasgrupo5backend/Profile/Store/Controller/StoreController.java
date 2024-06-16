package com.example.finanzasgrupo5backend.Profile.Store.Controller;

import com.example.finanzasgrupo5backend.Products.Model.ProductRequest;
import com.example.finanzasgrupo5backend.Products.Model.ProductResponse;
import com.example.finanzasgrupo5backend.Products.Service.IProductService;
import com.example.finanzasgrupo5backend.Profile.Store.Model.Store;
import com.example.finanzasgrupo5backend.Profile.Store.Model.StoreRequest;
import com.example.finanzasgrupo5backend.Profile.Store.Model.StoreResponse;
import com.example.finanzasgrupo5backend.Profile.Store.Service.IStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Store Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class StoreController {
    @Autowired
    private  IStoreService storeService;

    @Operation(summary = "Create a Store")
    @PostMapping("/stores")
    public ResponseEntity<StoreResponse> ingresar(
            @RequestParam(name = "userId") Long userId,
            @RequestBody StoreRequest storeRequest){
        storeService.createStore(userId, storeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    @Operation(summary = "Update a Store")
    @PutMapping("/stores")
    public ResponseEntity<StoreResponse> actualizar( @RequestBody StoreRequest storeRequest){
        ModelMapper m = new ModelMapper();
        Store store = m.map(storeRequest, Store.class);
        storeService.createStore(store);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    */

}
