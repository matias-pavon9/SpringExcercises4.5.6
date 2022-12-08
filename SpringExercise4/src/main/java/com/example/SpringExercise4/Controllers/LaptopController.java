package com.example.SpringExercise4.Controllers;

import com.example.SpringExercise4.Entities.Laptop;
import com.example.SpringExercise4.Repositories.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    @Autowired
    private LaptopRepository laptopRepository;
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    @GetMapping("/laptop/all")
    public ResponseEntity<List<Laptop>> findAll(){
        return ResponseEntity.ok(laptopRepository.findAll());
    }

    @GetMapping("/laptop/oneById")
    public ResponseEntity<Laptop> findOneById(@PathParam("id") Long id){
        Optional<Laptop> laptop = laptopRepository.findById(id);
        if(laptop.isPresent()){
            return ResponseEntity.ok(laptop.get());
        } else{
            log.warn("The Laptop with id '" + id + "' doesn't exist");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/laptop/create")
    @ApiOperation("Método para crear una Laptop en la base de datos")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){
        if (laptop.getId() == null) {
            laptopRepository.save(laptop);
            return ResponseEntity.status(HttpStatus.CREATED).body(laptop);
        } else {
            log.warn("You are trying to create a new Laptop with an Id");
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/laptop/update")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
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

    @DeleteMapping("/laptop/delete")
    public ResponseEntity<Laptop> delete(@PathParam("id") Long id){
        if(id == null || id.toString().isEmpty()) {
            log.error("The Id parameter is required");
            return ResponseEntity.badRequest().build();
        } else {
            Optional<Laptop> laptop = laptopRepository.findById(id);
            if(laptop.isPresent()) {
                laptopRepository.delete(laptop.get());
                return ResponseEntity.ok(laptop.get());
            } else {
                log.warn("The Laptop with id '" + id + "' doesn't exist");
                return ResponseEntity.badRequest().build();
            }
        }
    }
    @DeleteMapping("/laptop/deleteAll")
    public ResponseEntity<Laptop> deleteAll() {
        laptopRepository.deleteAll();
        log.info("All Laptops were deleted");
        return ResponseEntity.noContent().build();
    }

}
