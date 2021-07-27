package com.distribuidos.model;

import java.util.List;

public class Account {
    int number;
    float current_balance;
    User user;
    List<Transaction> recibidas;
    List<Transaction> realizadas;

    public Account() {
    }

    public Account(int number, float current_balance, User user) {
        this.number = number;
        this.current_balance = current_balance;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getRecibidas() {
        return recibidas;
    }

    public void setRecibidas(List<Transaction> recibidas) {
        this.recibidas = recibidas;
    }

    public List<Transaction> getRealizadas() {
        return realizadas;
    }

    public void setRealizadas(List<Transaction> realizadas) {
        this.realizadas = realizadas;
    }
}
