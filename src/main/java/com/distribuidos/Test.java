package com.distribuidos;

import com.distribuidos.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsondb.JsonDBTemplate;

import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        try {

//            JsonDBTemplate jsonDBTemplate = JsonDB.getDB();
//            User user = jsonDBTemplate.findById("V254049466", User.class);
//            System.out.println(user.getName());

//            AccountImplementation accountImplementation = new AccountImplementation();
//
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonInString = mapper.writeValueAsString(accountImplementation.checkUser("24635783"));
//            String jsonInString = mapper.writeValueAsString(accountImplementation.validateUser("caverde","123456"));
//            User user = new User();
//            user.setDocument_id("24635783");
//            user.setName("Alessandra Varisco");
//            user.setUsername("avarisco");
//            user.setPassword("654321");
//            String jsonInString = mapper.writeValueAsString(accountImplementation.createUser(user));
//            System.out.println(jsonInString);

            String dbFilesLocation = "src/main/resources/db/";
            String baseScanPackage = "com.distribuidos.model";

            JsonDBTemplate jsonDBTemplate = new JsonDBTemplate(dbFilesLocation, baseScanPackage);
            if (jsonDBTemplate.getCollection(User.class) == null)
                jsonDBTemplate.createCollection(User.class);
            if (jsonDBTemplate.getCollection(Account.class) == null)
                jsonDBTemplate.createCollection(Account.class);
            if (jsonDBTemplate.getCollection(Transaction.class) == null)
                jsonDBTemplate.createCollection(Transaction.class);



            Deposit deposit = new Deposit(100,"descrip", 0, 6);

            jsonDBTemplate.insert(deposit);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
