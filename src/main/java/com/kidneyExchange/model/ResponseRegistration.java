package com.kidneyExchange.model;


import lombok.Data;

@Data
public class ResponseRegistration {

    private String messege;

    private Boolean userIsInDatabase;

    public ResponseRegistration(String messege, Boolean userIsInDatabase) {
        this.messege = messege;
        this.userIsInDatabase = userIsInDatabase;
    }
}
