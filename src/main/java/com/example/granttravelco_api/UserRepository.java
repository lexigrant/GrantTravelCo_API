package com.example.granttravelco_api;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByGoogleSub(String googleSub);
}
