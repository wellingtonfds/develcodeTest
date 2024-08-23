package com.checkout.develcode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.checkout.develcode.exceptions.CustomFeignErrorDecoder;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomFeignErrorDecoder();
    }
}
