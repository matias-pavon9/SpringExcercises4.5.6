package com.example.SpringExercise4.Entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Laptops")
@ApiModel("Entidad Laptop para representar las distintas Laptops disponibles en el shop")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    @ApiModelProperty("Modelo (no marca) de la Laptop seleccionada")
    private String model;
    private LocalDate releaseDate;
    private String cpu;
    private String ram;
    private String storage;

    public Laptop() {
    }

    public Laptop(String brand, String model, LocalDate releaseDate, String cpu, String ram, String storage) {
        this.brand = brand;
        this.model = model;
        this.releaseDate = releaseDate;
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
}
