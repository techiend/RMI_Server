package com.carlosverde.model;

import java.util.Date;

public class Transaction {
    int id;
    float amount;
    Date date;
    String desc;
    Account source;
    Account destination;

    public Transaction() {
    }

    public Transaction(int id, float amount, Date date, String desc, Account source, Account destination) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.desc = desc;
        this.source = source;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Account getSource() {
        return source;
    }

    public void setSource(Account source) {
        this.source = source;
    }

    public Account getDestination() {
        return destination;
    }

    public void setDestination(Account destination) {
        this.destination = destination;
    }
}
