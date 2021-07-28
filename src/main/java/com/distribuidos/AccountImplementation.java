package com.distribuidos;

import com.distribuidos.datastorage.JsonDB;
import com.distribuidos.inOutObjects.OpenAccountOutput;
import com.distribuidos.inOutObjects.Response;
import com.distribuidos.model.Account;
import com.distribuidos.model.User;
import com.distribuidos.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsondb.JsonDBTemplate;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class AccountImplementation extends UnicastRemoteObject implements AccountService {
    AccountImplementation() throws RemoteException{}
    public String checkUser(String document_id) throws RemoteException {
        Response response = new Response();
        try{
            JsonDBTemplate jsonDBTemplate = JsonDB.getDB();
            User user = jsonDBTemplate.findById(document_id, User.class);

            if (user != null){

                String jxQuery = String.format("/.[user_id='%s']", document_id);
                List<Account> accountList = jsonDBTemplate.find(jxQuery, Account.class);

                OpenAccountOutput output = new OpenAccountOutput(user.getDocument_id(),accountList.size());
                response.setCod(0);
                response.setMsg("Usuario existe.");
                response.setData(output);
            }
            else{
                response.setCod(1);
                response.setMsg("Usuario no existe.");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            response.setCod(99);
            response.setMsg("Ha ocurrido un error: "+e.getLocalizedMessage());
        }
        
        String respuesta = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            respuesta = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    public String validateUser(String username, String password) throws RemoteException {
        Response response = new Response();
        try{
            JsonDBTemplate jsonDBTemplate = JsonDB.getDB();

            String jxQuery = String.format("/.[username='%s']", username);
            User user = jsonDBTemplate.findOne(jxQuery, User.class);

            if (user != null){

                if (user.getPassword().equals(password)){
                    response.setCod(0);
                    response.setMsg("Usuario validado exitosamente.");
                    response.setData(user);
                }
                else {
                    response.setCod(1);
                    response.setMsg("Usuario/clave incorrectos.");
                }
            }
            else {
                response.setCod(1);
                response.setMsg("Usuario no existe.");
            }

        }
        catch (Exception e){
            e.printStackTrace();
            response.setCod(99);
            response.setMsg("Ha ocurrido un error: "+e.getLocalizedMessage());
        }
        String respuesta = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            respuesta = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    public String createUser(User user) throws RemoteException {
        Response response = new Response();
        try{
            JsonDBTemplate jsonDBTemplate = JsonDB.getDB();

            if (user.getDocument_id() == null || user.getDocument_id().trim().equals("")){
                response.setCod(1);
                response.setMsg("Documento de identidad no puede estar vacio");
                String respuesta = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            respuesta = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return respuesta;
            }
            if (user.getName() == null || user.getName().trim().equals("")){
                response.setCod(1);
                response.setMsg("Nombre no puede estar vacio");
                String respuesta = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            respuesta = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return respuesta;
            }
            if (user.getUsername() == null || user.getUsername().trim().equals("")){
                response.setCod(1);
                response.setMsg("Usuario no puede estar vacio");
                String respuesta = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            respuesta = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return respuesta;
            }
            if (user.getPassword() == null || user.getPassword().trim().equals("")){
                response.setCod(1);
                response.setMsg("Clave no puede estar vacio");
                String respuesta = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            respuesta = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return respuesta;
            }

            User findUser = jsonDBTemplate.findById(user.getDocument_id(), User.class);

            if (findUser == null){

                jsonDBTemplate.insert(user);

                response.setCod(0);
                response.setMsg("Usuario registrado.");
                response.setData(user);

            }
            else{
                response.setCod(1);
                response.setMsg("Documento de identidad ya registrado");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            response.setCod(99);
            response.setMsg("Ha ocurrido un error: "+e.getLocalizedMessage());
        }
        String respuesta = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            respuesta = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    public String createAccount(String document_id, float montoInicial) throws RemoteException {
        Response response = new Response();
        try{
            JsonDBTemplate jsonDBTemplate = JsonDB.getDB();

            if (document_id == null || document_id.trim().equals("")){
                response.setCod(1);
                response.setMsg("Documento de identidad no puede estar vacio");
                String respuesta = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            respuesta = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return respuesta;
            }
            if (montoInicial <= 0){
                response.setCod(1);
                response.setMsg("Monto incial no es válido");
                String respuesta = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            respuesta = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return respuesta;
            }

            User user = jsonDBTemplate.findById(document_id, User.class);

            if (user != null){

                Account account = new Account(user.getDocument_id());

                //TODO - Realizar deposito inicial
                account.setCurrent_balance(montoInicial);

                response.setCod(0);
                response.setMsg("Usuario registrado.");
                response.setData(account);

            }
            else{
                response.setCod(1);
                response.setMsg("Documento de identidad ya registrado");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            response.setCod(99);
            response.setMsg("Ha ocurrido un error: "+e.getLocalizedMessage());
        }
        String respuesta = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            respuesta = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
    public String getAccount(int number) throws RemoteException {
        Response response = new Response();
        String respuesta = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            respuesta = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
}
