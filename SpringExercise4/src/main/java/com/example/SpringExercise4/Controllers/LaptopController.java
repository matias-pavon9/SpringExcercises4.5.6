package com.example.SpringExercise4.Controllers;

import com.example.SpringExercise4.Entities.Laptop;
import com.example.SpringExercise4.Repositories.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LaptopController {

    @Autowired
    private LaptopRepository laptopRepository;
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    @GetMapping("/laptop/all")
    public ResponseEntity<List<Laptop>> ListAllLaptops(){
        return ResponseEntity.ok(laptopRepository.findAll());
    }

    @PostMapping("/laptop/create")
    public ResponseEntity<Laptop> CreateLaptop(@RequestBody Laptop laptop){
        if (laptop.getId() == null) {
            laptopRepository.save(laptop);
            return ResponseEntity.status(HttpStatus.CREATED).body(laptop);
        } else {
            log.warn("You are trying to create a new Laptop with an Id");
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/laptop/update")
    public ResponseEntity<Laptop> UpdateLaptop(@RequestBody Laptop laptop){
        if (laptop.getId() == null){
            log.warn("You are trying to update a Laptop without an Id");
            return ResponseEntity.badRequest().build();
        } else if (!laptopRepository.existsById(laptop.getId())){
            log.warn("You are trying to update a non existing Laptop");
            return ResponseEntity.notFound().build();
        } else {
            laptopRepository.save(laptop);
            return ResponseEntity.ok(laptop);
        }
    }

}
