package com.weather.weatheApi.service;

import com.weather.weatheApi.DTO.WeatherObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
