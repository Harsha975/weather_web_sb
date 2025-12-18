package com.weather.weatheApi.service;

import com.weather.weatheApi.DTO.PostDummyObject;
import com.weather.weatheApi.DTO.User;
import com.weather.weatheApi.DTO.WeatherObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;
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

    public List<User> GetUser(){
        String URl = "http://localhost:8080/api/getUser";

        ResponseEntity<List<User>> response = restTemplate.exchange(URl,HttpMethod.GET ,null , new ParameterizedTypeReference<List<User>>() {} );

        List<User> res_obj = response.getBody();

        return res_obj;

    }

    public User CreateUser(@RequestBody String Request){
        String URl = "http://localhost:8080/api/createUser";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(Request , headers);
        ResponseEntity<User> repsonse = restTemplate.exchange(URl , HttpMethod.POST , request , User.class);
        return repsonse.getBody();
    }
}
