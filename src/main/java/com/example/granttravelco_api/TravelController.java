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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
@RequestMapping("/api")
public class TravelController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GrantTravelCoRepository grantTravelCoRepository;

   @GetMapping("/user/{userId}/travel")
    public ResponseEntity<List<Travel>> getAllTravelByUserId(@PathVariable(value = "userId")Integer userId) {
       if (!userRepository.existsById(userId)) {
           throw new ResourceNotFoundException("Not found User with id = " + userId);
       }
       List<Travel> travels = grantTravelCoRepository.findByUserId(userId);
       return new ResponseEntity<>(travels, HttpStatus.OK);
   }
   @GetMapping("/travel/{id}")
    public ResponseEntity<Travel> getTravelByUserId(@PathVariable(value = "id")Integer id) {
       Travel travel = grantTravelCoRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Not found Travel with id = " + id));
       return new ResponseEntity<>(travel, HttpStatus.OK);
   }
   @PostMapping("/user/{userId}/travel")
    public ResponseEntity<Travel> createTravel(@PathVariable(value = "userId")Integer userId,
                                               @RequestBody Travel travelRequest) {
       Travel travel = userRepository.findById(userId).map(user -> {
           travelRequest.setUser(user);
           return grantTravelCoRepository.save(travelRequest);
       }).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));
       return new ResponseEntity<>(travel, HttpStatus.CREATED);
   }
   @PutMapping("/travel/{id}")
    public ResponseEntity<Travel> updateTravel(@PathVariable("id")Integer id, @RequestBody Travel travelRequest) {
       Travel travel = grantTravelCoRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("TravelId " + id + "not found"));
       travel.setAddress(travelRequest.getAddress());
       travel.setDiaryEntry(travelRequest.getDiaryEntry());
       travel.setLatitude(travelRequest.getLatitude());
       travel.setLongitude(travelRequest.getLongitude());
       travel.setLocation(travelRequest.getLocation());
       travel.setEntryDate(travelRequest.getEntryDate());
       return new ResponseEntity<>(grantTravelCoRepository.save(travel), HttpStatus.OK);
   }
   @DeleteMapping("/travel/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id")Integer id) {
       grantTravelCoRepository.deleteById(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
   @DeleteMapping("/user/{userId}/travel")
    public ResponseEntity<List<Travel>> deleteAllTravelofUser(@PathVariable(value = "userId")Integer userId) {
       if(!userRepository.existsById(userId)) {
           throw new ResourceNotFoundException("Not found User with id = " + userId);
       }
       grantTravelCoRepository.deleteByUserId(userId);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}
