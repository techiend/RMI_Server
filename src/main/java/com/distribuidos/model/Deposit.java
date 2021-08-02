package com.distribuidos.model;

import io.jsondb.annotation.Document;

import java.util.Date;

@Document(collection = "transaction", schemaVersion= "1.0")
public class Deposit extends Transaction{
    public Deposit() {
    }

    public Deposit(float amount, String desc, int sourceNumber, int destinationNumber) {
        super(amount, desc, sourceNumber, destinationNumber, "Deposit");
    }
}
