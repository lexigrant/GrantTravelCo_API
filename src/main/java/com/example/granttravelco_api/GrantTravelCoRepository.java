package com.example.granttravelco_api;


import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

public interface GrantTravelCoRepository extends CrudRepository<Travel, Integer> {
    List<Travel> findByUserId(Integer id);

    @Transactional
    void deleteByUserId(Integer id);
}
