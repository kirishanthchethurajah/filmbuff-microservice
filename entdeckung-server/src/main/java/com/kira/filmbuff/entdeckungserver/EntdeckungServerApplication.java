package com.kira.filmbuff.entdeckungserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EntdeckungServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntdeckungServerApplication.class, args);
    }

}
