package com.example.SpringExercise4.Controllers;

import com.example.SpringExercise4.Entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import javax.swing.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;
    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Comprobar Método findAll del Controlador 'LaptopController'")
    @Test
    void findAll() {
        create();

        ResponseEntity<Laptop[]> response =
                testRestTemplate.getForEntity("/laptop/all", Laptop[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        List<Laptop> laptops = Arrays.asList(response.getBody());
        //Línea para testear método por método
        //assertEquals(1, laptops.size());
        //Línea para testear todos los métodos en una ejecución
        assertEquals(2, laptops.size());
    }

    @Test
    void findOneById() {
        create();
        ResponseEntity<Laptop> response =
                //Línea para testear método por método
                //testRestTemplate.getForEntity("/laptop/oneById?id=1", Laptop.class);
                //Línea para testear todos los métodos en una ejecución
                testRestTemplate.getForEntity("/laptop/oneById?id=4", Laptop.class);
        Laptop result = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        //Línea para testear método por método
        //assertEquals(1L,result.getId());
        //Línea para testear todos los métodos en una ejecución
        assertEquals(4L, result.getId());
        assertEquals("SpringTestLaptop", result.getBrand());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = "{\n" +
                "    \"brand\": \"SpringTestLaptop\",\n" +
                "    \"model\": \"SM-550T\",\n" +
                "    \"releaseDate\": \"2022-02-25\",\n" +
                "    \"cpu\": \"Intel Core I7\",\n" +
                "    \"ram\": \"32Gb\",\n" +
                "    \"storage\": \"1TB SSD\"\n" +
                "}";

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response =
                testRestTemplate.exchange("/laptop/create", HttpMethod.POST, request, Laptop.class);
        Laptop result = response.getBody();
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(201, response.getStatusCodeValue());
        //Deshabilitar la siguiente línea para testear todos los test de la clase en una ejecución.
        //Habilitar para testear método por método
        //assertEquals(1L,result.getId());
        assertEquals("SpringTestLaptop", result.getBrand());
    }

    @Test
    void update() {
        create();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = "{\n" +
                //Pasar "id": 1 para testear método por método,
                //pasar "id": 3 para testear todos los métodos en una ejecución
                "    \"id\": 3,\n" +
                "    \"brand\": \"SpringTestLaptop Cambiado\",\n" +
                "    \"model\": \"SM-550T\",\n" +
                "    \"releaseDate\": \"2022-02-25\",\n" +
                "    \"cpu\": \"Intel Core I7\",\n" +
                "    \"ram\": \"32Gb\",\n" +
                "    \"storage\": \"1TB SSD\"\n" +
                "}";

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response =
                testRestTemplate.exchange("/laptop/update", HttpMethod.PUT, request, Laptop.class);
        Laptop result = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals( "SpringTestLaptop Cambiado", result.getBrand());
    }

    @Test
    void delete() {
        create();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<Laptop> response =
                testRestTemplate.exchange("/laptop/delete?id=1", HttpMethod.DELETE, request, Laptop.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        ResponseEntity<Laptop[]> response2 =
                testRestTemplate.getForEntity("/laptop/all", Laptop[].class);
        List<Laptop> laptops = Arrays.asList(response2.getBody());
        //Línea para testear método por método
        //assertEquals(0, laptops.size());
        //Línea para testear todos los métodos en una ejecución
        assertEquals(1, laptops.size());
    }

    @Test
    void deleteAll() {
        create();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<Laptop> response =
                testRestTemplate.exchange("/laptop/deleteAll", HttpMethod.DELETE, request, Laptop.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(204, response.getStatusCodeValue());
        ResponseEntity<Laptop[]> response2 =
                testRestTemplate.getForEntity("/laptop/all", Laptop[].class);
        List<Laptop> laptops = Arrays.asList(response2.getBody());
        assertEquals(0, laptops.size());
    }
}