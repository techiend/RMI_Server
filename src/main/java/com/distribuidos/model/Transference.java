package com.distribuidos.model;

import io.jsondb.annotation.Document;

import java.util.Date;

@Document(collection = "transaction", schemaVersion= "1.0")
public class Transference extends Transaction{
    public Transference() {
    }

    public Transference(float amount, Date date, String desc, int sourceNumber, int destinationNumber) {
        super(amount, date, desc, sourceNumber, destinationNumber, "Transference");
    }
}
