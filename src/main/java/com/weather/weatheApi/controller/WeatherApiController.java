package com.weather.weatheApi.controller;

import com.weather.weatheApi.DTO.Users;
import com.weather.weatheApi.service.WeatherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherApiController {
    @Autowired
    WeatherService service;
    @GetMapping("/getWeather")
    public ResponseEntity<?> getWeather(){
        return new ResponseEntity<>(service.getWeather("Mumbai"), HttpStatus.OK);
    }

    @PostMapping("/postDummy")
    public ResponseEntity<?> postDummy(){
        return new ResponseEntity<>(service.getPostData() , HttpStatus.OK);
    }

    @GetMapping("/api/getUser/{id}")
    public ResponseEntity<?> getUsers(@PathVariable Long id){
        return new ResponseEntity<>(service.getUserById(id) ,HttpStatus.OK);
    }

    @GetMapping("/api/getUsers")
    public ResponseEntity<?> getUsers(){
        return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
    }

    //the csrf-cross-site-repsonse-forging it doest allow user to update put patch post methods without this token
    @GetMapping("/api/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUsers(@RequestBody Users userData){
        return new ResponseEntity<>(service.createUser(userData) ,HttpStatus.OK);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Users user){
        return service.verifyUser(user);
    }


}
