package net.focik.gasmain.domain;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class GasMainConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    @LoadBalanced //sprawia, że RestTemplate pyta o url Eureka serwer
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
