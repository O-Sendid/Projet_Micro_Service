package com.miage.alom.game_ui.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;


@Configuration 
public class RestConfiguration {
	   
	private String username;

    private String password;

    @Value("${trainer.service.username}")
    void setUsername(String username) {
        this.username = username;
    }

    @Value("${trainer.service.password}")
    void setPassword(String password) {
        this.password = password;
    }
    
    @Bean
    RestTemplate trainerApiRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        BasicAuthenticationInterceptor interceptor = new BasicAuthenticationInterceptor(this.username, this.password);
        restTemplate.setInterceptors(Arrays.asList(interceptor));
        return restTemplate;
    }
    

    @Bean
    RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Arrays.asList((httpRequest, bytes, clientHttpRequestExecution) -> {
            httpRequest.getHeaders().setAcceptLanguageAsLocales(List.of(LocaleContextHolder.getLocale()));
            return  clientHttpRequestExecution.execute(httpRequest, bytes);
        }));
        return restTemplate;
    }



}