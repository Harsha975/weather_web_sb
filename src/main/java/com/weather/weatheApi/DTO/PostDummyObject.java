package com.weather.weatheApi.DTO;


import lombok.Data;

import java.util.List;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */

@Data
public class PostDummyObject{
    public List<Post> posts;
    public int total;
    public int skip;
    public int limit;

    @Data
    public static class Post{
        public int id;
        public String title;
        public String body;
        public List<String> tags;
        public Reactions reactions;
        public int views;
        public int userId;
    }

    @Data
    public static class Reactions{
        public int likes;
        public int dislikes;
    }
}





