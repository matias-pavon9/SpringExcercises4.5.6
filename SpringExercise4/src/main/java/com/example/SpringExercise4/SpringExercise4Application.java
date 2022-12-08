package com.example.SpringExercise4;

import com.example.SpringExercise4.Entities.Laptop;
import com.example.SpringExercise4.Repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class SpringExercise4Application {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(SpringExercise4Application.class, args);
		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);

		laptopRepository.save(new Laptop("MSI", "Katana", LocalDate.of(2020, 12,03), "Intel Core I5", "16Gb", "500MB SSD"));
		laptopRepository.save(new Laptop("Dell", "NewDellModell", LocalDate.of(2021, 10,20), "Intel Core I3", "8Gb", "500MB SSD"));
		laptopRepository.save(new Laptop("HP", "HP-5920", LocalDate.of(2018, 9,05), "AMD Ryzen 3", "18Gb", "500MB SSD"));
	}

}
