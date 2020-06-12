package com.kidneyExchange.model;


import lombok.Data;

@Data
public class JwtRequestRegistration {

    private String username;

    private String email;

    private String password;

    private String type;
}
