package com.distribuidos.implementation;

import com.distribuidos.datastorage.JsonDB;
import com.distribuidos.inOutObjects.OpenAccountOutput;
import com.distribuidos.inOutObjects.Response;
import com.distribuidos.model.Account;
import com.distribuidos.model.User;
import com.distribuidos.service.AccountService;
import io.jsondb.JsonDBTemplate;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class AccountImplementation extends UnicastRemoteObject implements AccountService {
    public AccountImplementation() throws RemoteException{}
    public Response checkUser(String document_id) throws RemoteException {
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
        return response;
    }
    public Response validateUser(String username, String password) throws RemoteException {
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
        return response;
    }
    public Response createAccount(User user) throws RemoteException {
        Response response = new Response();
        return response;
    }
    public Response getAccount(int number) throws RemoteException {
        Response response = new Response();
        return response;
    }
}
