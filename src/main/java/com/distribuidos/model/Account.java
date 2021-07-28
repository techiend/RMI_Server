package com.distribuidos.model;

import com.distribuidos.datastorage.JsonDB;
import io.jsondb.JsonDBTemplate;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

import java.util.List;
import java.util.Random;

@Document(collection = "account", schemaVersion= "1.0")
public class Account {
    @Id
    int number;
    float current_balance;
    String user_id;

    public Account(String user_id) {
        setNumber();
        this.user_id = user_id;
    }

    public int getNumber() {
        return number;
    }

    private void setNumber() {
        JsonDBTemplate jsonDBTemplate = JsonDB.getDB();
        List<Account> accounts = jsonDBTemplate.findAll( Account.class);
        Account last = accounts.get(accounts.size()-1);
        this.number = last.getNumber()+1;
    }

    public float getCurrent_balance() {
        return current_balance;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setCurrent_balance(float current_balance){
        this.current_balance=current_balance;
    }

}
