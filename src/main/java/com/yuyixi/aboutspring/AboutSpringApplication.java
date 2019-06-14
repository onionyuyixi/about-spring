package com.yuyixi.aboutspring;

import com.yuyixi.aboutspring.ioc.postprocessors.Demo4Instance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class AboutSpringApplication {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.refresh();
        context.getBean(Demo4Instance.class);
        SpringApplication.run(AboutSpringApplication.class, args);
    }


}
