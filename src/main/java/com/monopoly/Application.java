package com.monopoly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

//@Controller
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

//    @RequestMapping("/")
//    @ResponseBody
//    String home() {
//        return "Hello World!";
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}