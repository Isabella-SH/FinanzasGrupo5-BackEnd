package com.example.finanzasgrupo5backend.Profile.Store.Repository;
import com.example.finanzasgrupo5backend.Profile.Store.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStoreRepository extends JpaRepository<Store,Long> {

}
