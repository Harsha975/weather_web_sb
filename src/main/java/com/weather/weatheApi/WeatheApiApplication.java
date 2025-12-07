package com.weather.weatheApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WeatheApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatheApiApplication.class, args);
	}
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
//    @GetMapping("/")
//        public String hello(@RequestParam(value = "name" , defaultValue = "World") String name){
//        return String.format("hello %s", name);
//    }
}