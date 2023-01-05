package com.example.workshop2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Workshop2Application {

    public static void main(String[] args) {
        SpringApplication.run(Workshop2Application.class, args);
    }

}


