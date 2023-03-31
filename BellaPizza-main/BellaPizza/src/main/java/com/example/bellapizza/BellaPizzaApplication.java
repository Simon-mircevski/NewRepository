package com.example.bellapizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class BellaPizzaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BellaPizzaApplication.class, args);
    }

}
