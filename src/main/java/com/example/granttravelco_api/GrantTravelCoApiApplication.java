package com.example.granttravelco_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@SpringBootApplication
@RestController
public class GrantTravelCoApiApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GrantTravelCoRepository grantTravelCoRepository;
    public static void main(String[] args) {
        SpringApplication.run(GrantTravelCoApiApplication.class, args);
    }



   @GetMapping("/travel")
    public Iterable<Travel> travelIndex() {return grantTravelCoRepository.findAll();}

    @PostMapping("/travel")
    public Iterable<Travel> create (@RequestBody Travel travelData) {
        grantTravelCoRepository.save(travelData);
        return grantTravelCoRepository.findAll();
    }

    @DeleteMapping("/travel/{id}")
    public Iterable<Travel> deleteTravel(@PathVariable int id) {
        grantTravelCoRepository.deleteById(id);
        return grantTravelCoRepository.findAll();
    }

    @PutMapping("/travel/{id}")
    public Iterable<Travel> update(@PathVariable int id, @RequestBody Travel travelData) {
        travelData.setId(id);
        grantTravelCoRepository.save(travelData);
        return grantTravelCoRepository.findAll();
    }

    @GetMapping("/user")
    public Iterable<User> userIndex() {return userRepository.findAll();}

    @PostMapping("/user")
    public Iterable<User> create (@RequestBody User userData) {
        userRepository.save(userData);
        return userRepository.findAll();
    }

    @DeleteMapping("/user/{id}")
    public Iterable<User> deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return userRepository.findAll();
    }

    @PutMapping("/user/{id}")
    public Iterable<User> update(@PathVariable int id, @RequestBody User userData) {
        userData.setId(id);
        userRepository.save(userData);
        return userRepository.findAll();
    }

    @GetMapping("/user/googleSub/{googleSub}")
    public User getUserByGoogleSub(@PathVariable String googleSub) {
        return userRepository.findByGoogleSub(googleSub);
    }
}
