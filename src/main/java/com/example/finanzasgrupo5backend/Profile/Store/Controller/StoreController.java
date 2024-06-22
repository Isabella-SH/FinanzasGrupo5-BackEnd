package com.example.finanzasgrupo5backend.Profile.Store.Controller;

import com.example.finanzasgrupo5backend.Profile.Store.Model.StoreRequest;
import com.example.finanzasgrupo5backend.Profile.Store.Model.StoreResponse;
import com.example.finanzasgrupo5backend.Profile.Store.Service.IStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "Obtain a list of all Stores")
    @GetMapping("/stores")
    public ResponseEntity<List<StoreResponse>> getAllStores() {
        var res = storeService.getAllStores();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Obtain a list of Store by user Id")
    @GetMapping("/stores/user/{id}")
    public ResponseEntity<List<StoreResponse>> getStoreByUserId(@PathVariable(name = "id") Long user_id) {
        var res = storeService.getStoreByUserId(user_id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update a Store")
    @PatchMapping("/stores/{id}")
    public ResponseEntity<StoreResponse> updateStore(@PathVariable(name = "id") Long storeId, @RequestParam String name) {
        long userId=1;
        var res = storeService.updateStore(storeId, name, Long.valueOf(userId));
        if (res == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }
    @Operation(summary = "Delete a Store")
    @DeleteMapping("/stores/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable(name = "id") Long storeId) {
        storeService.deleteStore(storeId);
        return ResponseEntity.noContent().build();
    }

}
