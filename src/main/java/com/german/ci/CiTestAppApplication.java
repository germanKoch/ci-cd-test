package com.german.ci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CiTestAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CiTestAppApplication.class, args);
    }

}
