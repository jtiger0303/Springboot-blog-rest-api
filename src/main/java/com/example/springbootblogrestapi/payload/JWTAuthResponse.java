package com.example.springbootblogrestapi.payload;

import lombok.Data;

@Data
public class JWTAuthResponse {
    private String accessToken;
    private String tokenType="Bearer";

}
