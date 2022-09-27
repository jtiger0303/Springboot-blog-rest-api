package com.example.springbootblogrestapi.payload;

import lombok.Data;

//payload=dtos//
@Data
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;

}
