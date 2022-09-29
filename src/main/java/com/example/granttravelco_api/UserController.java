package com.example.granttravelco_api;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")

public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        if(users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id")Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found User with id = " + id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User _user = userRepository.save(new User(user.getEmail(), user.getName(), user.getPicture(), user.getGoogleSub()));
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id")Integer id, @RequestBody User user) {
        User _user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found User with id = " + id));
        _user.setEmail(user.getEmail());
        _user.setName(user.getName());
        _user.setGoogleSub(user.getGoogleSub());
        return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id")Integer id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/user")
    public ResponseEntity<HttpStatus> deleteAllUser() {
        userRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/user/googleSub/{googleSub}")
    public ResponseEntity<User> findByGoogleSub(@PathVariable("googleSub")String googleSub) {
        User user = userRepository.findByGoogleSub(googleSub);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
