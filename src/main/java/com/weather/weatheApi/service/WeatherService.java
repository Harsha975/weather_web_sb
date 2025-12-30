package com.weather.weatheApi.service;

import com.weather.weatheApi.DTO.PostDummyObject;
import com.weather.weatheApi.DTO.Users;
import com.weather.weatheApi.DTO.WeatherObject;
import com.weather.weatheApi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository repository;
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtService jwtService;
    private String WEATHER_API = "b29dc82d94b2fae4ca117e00cee7b204";
    private String URL = "http://api.openweathermap.org/data/2.5/weather?q=CITY&APPID=WEATHER_API";

    public WeatherObject getWeather(String city){
        String final_Url = URL.replace("CITY",city).replace("WEATHER_API", WEATHER_API);
        ResponseEntity<WeatherObject> response = restTemplate.exchange(final_Url , HttpMethod.GET , null ,WeatherObject.class);
        WeatherObject Weather_obj = response.getBody();
        return Weather_obj;
    }
    public PostDummyObject getPostData(){
        String finalUrl = "https://node-fake-api-server.herokuapp.com/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-FakeAPI-Action" , "register");

        Map<String , Object> map = new HashMap<>();
        map.put("external_id" , "postman");

        HttpEntity<Map<String , Object>> request = new HttpEntity<>(map, headers);

        ResponseEntity<PostDummyObject> response = restTemplate.exchange(finalUrl , HttpMethod.POST, request, PostDummyObject.class);

        PostDummyObject data = response.getBody();

        return data;
    }

//    public List<User> GetUser(){
//        String URl = "http://localhost:8080/api/getUser";
//
//        ResponseEntity<List<User>> response = restTemplate.exchange(URl,HttpMethod.GET ,null , new ParameterizedTypeReference<List<User>>() {} );
//
//        List<User> res_obj = response.getBody();
//
//        return res_obj;
//
//    }
//
//    public User CreateUser(@RequestBody String Request){
//        String URl = "http://localhost:8080/api/createUser";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity request = new HttpEntity(Request , headers);
//        try{
//        ResponseEntity<User> repsonse = restTemplate.exchange(URl , HttpMethod.POST , request , User.class);
//            return repsonse.getBody();
//        } catch (HttpStatusCodeException e){
//            throw new ResponseStatusException(
//                    e.getStatusCode(),
//                    e.getResponseBodyAsString(),
//                    e
//            );
//        }

    public Optional<Users> getUserById(@RequestParam Long id){
        return repository.findById(id);
    }

    public List<Users> getUsers(){
        return repository.findAll();
    }

    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);

    public Users createUser(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }


    public String verifyUser(Users user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "Authentication failed for user " + user.getUsername();
        }
    }
}
