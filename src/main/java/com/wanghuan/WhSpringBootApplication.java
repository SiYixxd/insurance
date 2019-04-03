package com.wanghuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WhSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhSpringBootApplication.class, args);
    }
}
