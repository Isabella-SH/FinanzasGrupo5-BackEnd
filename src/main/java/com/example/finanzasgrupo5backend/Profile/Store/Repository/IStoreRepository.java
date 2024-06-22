package com.example.finanzasgrupo5backend.Profile.Store.Repository;
import com.example.finanzasgrupo5backend.Profile.Store.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStoreRepository extends JpaRepository<Store,Long> {

    List<Store> findStoreByUserId(Long user_id);
    List<Store> findAll();

    List<Store> findById(long id);

}
