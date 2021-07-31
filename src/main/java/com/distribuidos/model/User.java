package com.distribuidos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "user", schemaVersion= "1.0")
public class User {
    @Id
    String document_id;
    String name;
    String username;
    String password;

    public User() {
    }

    public User(String document_id, String name, String username, String password) {
        this.document_id = document_id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getDocument_id() {
        return document_id;
    }

    public void setDocument_id(String document_id) {
        this.document_id = document_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
