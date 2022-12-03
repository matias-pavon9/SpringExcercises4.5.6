package com.example.SpringExercise4.Controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> Saludar(){
        return ResponseEntity.ok("Hola a todos! Saludos desde HelloController");
    }

}
