package com.distribuidos.inOutObjects;

import com.distribuidos.model.Transaction;

import java.util.List;

public class AccountOutput {
    int number;
    float current_balance;
    String user_id;
    List<Transaction> transactions;

    public AccountOutput() {
    }

    public AccountOutput(int number, float current_balance, String user_id) {
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
