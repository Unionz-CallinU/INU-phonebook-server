package com.example.inuphonebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class InuPhonebookApplication {

    public static void main(String[] args) {
        SpringApplication.run(InuPhonebookApplication.class, args);
    }

}
