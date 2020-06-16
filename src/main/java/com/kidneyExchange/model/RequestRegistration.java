package com.kidneyExchange.model;


import lombok.Data;

@Data
public class RequestRegistration {

    private String username;

    private String email;

    private String password;

    private String type;
}
