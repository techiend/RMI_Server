package com.distribuidos;

import com.distribuidos.datastorage.JsonDB;
import com.distribuidos.implementation.AccountImplementation;
import com.distribuidos.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsondb.JsonDBTemplate;

public class Test {
    public static void main(String[] args) {

        try {

//            JsonDBTemplate jsonDBTemplate = JsonDB.getDB();
//            User user = jsonDBTemplate.findById("V254049466", User.class);
//            System.out.println(user.getName());

            AccountImplementation accountImplementation = new AccountImplementation();

            ObjectMapper mapper = new ObjectMapper();
//            String jsonInString = mapper.writeValueAsString(accountImplementation.checkUser("V25409466"));
            String jsonInString = mapper.writeValueAsString(accountImplementation.validateUser("caverde","123456"));
            System.out.println(jsonInString);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
