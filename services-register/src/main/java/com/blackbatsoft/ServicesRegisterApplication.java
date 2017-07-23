package com.blackbatsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServicesRegisterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicesRegisterApplication.class, args);
    }
}
