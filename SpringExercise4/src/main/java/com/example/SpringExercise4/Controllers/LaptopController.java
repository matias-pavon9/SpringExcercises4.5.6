package com.example.SpringExercise4.Controllers;

import com.example.SpringExercise4.Entities.Laptop;
import com.example.SpringExercise4.Repositories.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LaptopController {

    @Autowired
    LaptopRepository laptopRepository;

    @GetMapping("/laptop/all")
    public ResponseEntity<List<Laptop>> ListAllLaptops(){
        return ResponseEntity.ok(laptopRepository.findAll());
    }

    @PostMapping("/laptop/create")
    public ResponseEntity<Laptop> CreateLaptop(@RequestBody Laptop laptop){
        laptopRepository.save(laptop);
        return ResponseEntity.status(HttpStatus.CREATED).body(laptop);
    }

}
