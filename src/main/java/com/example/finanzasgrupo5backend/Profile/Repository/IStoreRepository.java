package com.example.finanzasgrupo5backend.Profile.Repository;
import com.example.finanzasgrupo5backend.Profile.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStoreRepository extends JpaRepository<Store,Long> {

}
