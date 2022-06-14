package com.blogafac.kocirfan;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


//        (exclude = {
//      org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
//       org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class}
//)
@SpringBootApplication
public class BlogafacApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogafacApplication.class, args);

    }


}
