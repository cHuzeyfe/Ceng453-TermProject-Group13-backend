package com.example.Ceng453.TermProject.Group13.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Ceng453TermProjectGroup13BackendApplication {

    @RequestMapping("/")
    @ResponseBody
    String home() {
      return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(Ceng453TermProjectGroup13BackendApplication.class, args);
    }
}
