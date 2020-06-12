package com.kidneyExchange.model;


import lombok.Data;

@Data
public class JwtResponseRegistration {

    private String messege;

    private Boolean userIsInDatabase;

    public JwtResponseRegistration(String messege, Boolean userIsInDatabase) {
        this.messege = messege;
        this.userIsInDatabase = userIsInDatabase;
    }
}
