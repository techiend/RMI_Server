package com.distribuidos.model;

import io.jsondb.annotation.Document;

import java.util.Date;

@Document(collection = "transaction", schemaVersion= "1.0")
public class Withdrawal extends Transaction{
    public Withdrawal() {
    }

    public Withdrawal(float amount, String desc, int sourceNumber, int destinationNumber) {
        super(amount, desc, sourceNumber, destinationNumber, "Withdrawal");
    }
}
