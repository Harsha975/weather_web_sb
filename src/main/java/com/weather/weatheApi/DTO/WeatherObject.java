package com.weather.weatheApi.DTO;

import lombok.Data;

import java.util.List;

@Data
public class WeatherObject{
    private Coord coord;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private String name;

    @Data
    public static class Coord{
        public double lon;
        public double lat;
    }
    @Data
    public static class Main{
        public double temp;
        public double feels_like;
        public double temp_min;
        public double temp_max;
        public int pressure;
        public int humidity;
        public int sea_level;
    }
    @Data
    public static class Weather{
        public int id;
        public String main;
        public String description;
        public String icon;
    }
    @Data
    public static class Wind{
        public double speed;
        public int deg;
    }
}
