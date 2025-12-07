package com.weather.weatheApi.controller;

import com.weather.weatheApi.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherApiController {
    @Autowired
    WeatherService service;
    @GetMapping("/getWeather")
        public ResponseEntity<?> getWeather(){
            return new ResponseEntity<>(service.getWeather("Mumbai"), HttpStatus.OK);
        }
}
