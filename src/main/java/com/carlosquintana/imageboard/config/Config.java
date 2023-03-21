package com.carlosquintana.imageboard.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean(name="mapper")
    public ModelMapper getModelMapper () {
        return new ModelMapper();
    }
}
