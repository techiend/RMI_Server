package com.distribuidos.model;

import io.jsondb.annotation.Document;

import java.util.Date;

@Document(collection = "transaction", schemaVersion= "1.0")
public class Deposit extends Transaction{
    public Deposit(int id, float amount, Date date, String desc, int sourceNumber, int destinationNumber) {
        super(id, amount, date, desc, sourceNumber, destinationNumber, "Deposit");
    }
}
