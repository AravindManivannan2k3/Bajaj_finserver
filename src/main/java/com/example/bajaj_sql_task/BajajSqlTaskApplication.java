package com.example.bajaj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BajajSqlTaskApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BajajSqlTaskApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE); // 🚀 no Tomcat
        app.run(args);
    }
}
