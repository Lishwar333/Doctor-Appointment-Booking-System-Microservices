package com.microservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

 
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients 
public class DoctorServiceApplication {

 

    public static void main(String[] args) {
        SpringApplication.run(DoctorServiceApplication.class, args);
    }

 

}