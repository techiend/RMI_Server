package com.distribuidos.datastorage;

import com.distribuidos.model.Account;
import com.distribuidos.model.Transaction;
import com.distribuidos.model.User;
import io.jsondb.JsonDBTemplate;

public class JsonDB {

    private static JsonDBTemplate jsonDBTemplate;

    static {
        try{
            String dbFilesLocation = "src/main/resources/db/";
            String baseScanPackage = "com.distribuidos.model";

            jsonDBTemplate = new JsonDBTemplate(dbFilesLocation, baseScanPackage);
            if (jsonDBTemplate.getCollection(User.class) == null)
                jsonDBTemplate.createCollection(User.class);
            if (jsonDBTemplate.getCollection(Account.class) == null)
                jsonDBTemplate.createCollection(Account.class);
            if (jsonDBTemplate.getCollection(Transaction.class) == null)
                jsonDBTemplate.createCollection(Transaction.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static JsonDBTemplate getDB() {
        return jsonDBTemplate;
    }

}
