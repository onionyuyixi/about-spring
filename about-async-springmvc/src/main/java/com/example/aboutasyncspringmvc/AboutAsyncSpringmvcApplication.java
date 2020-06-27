package com.example.aboutasyncspringmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;

@EnableAsync
@SpringBootApplication
@WebFilter(asyncSupported = true)
@WebServlet(asyncSupported = true)
public class AboutAsyncSpringmvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(AboutAsyncSpringmvcApplication.class, args);
    }

}
