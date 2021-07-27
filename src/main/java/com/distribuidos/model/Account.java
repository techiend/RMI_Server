package com.distribuidos.model;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

import java.util.List;

@Document(collection = "account", schemaVersion= "1.0")
public class Account {
    @Id
    int number;
    float current_balance;
    String user_id;

    public Account() {
    }

    public Account(int number, float current_balance, String user_id) {
        this.number = number;
        this.current_balance = current_balance;
        this.user_id = user_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(float current_balance) {
        this.current_balance = current_balance;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
