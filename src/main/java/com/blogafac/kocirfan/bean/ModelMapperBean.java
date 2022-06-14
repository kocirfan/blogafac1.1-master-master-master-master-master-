package com.blogafac.kocirfan.bean;

import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperBean {



    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }


}
