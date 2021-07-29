package com.distribuidos;

import com.distribuidos.datastorage.JsonDB;
import com.distribuidos.datastorage.Utilidades;
import com.distribuidos.inOutObjects.AccountOutput;
import com.distribuidos.inOutObjects.OpenAccountOutput;
import com.distribuidos.inOutObjects.Response;
import com.distribuidos.inOutObjects.UserOutput;
import com.distribuidos.model.Account;
import com.distribuidos.model.Transaction;
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

        return Utilidades.generateResponse(response);
    }
    public String validateUser(String username, String password) throws RemoteException {
        Response response = new Response();
        try{
            JsonDBTemplate jsonDBTemplate = JsonDB.getDB();

            String userQuery = String.format("/.[username='%s']", username);
            User user = jsonDBTemplate.findOne(userQuery, User.class);

            if (user != null){

                if (user.getPassword().equals(password)){

                    UserOutput userOutput = new UserOutput();
                    userOutput.setDocument_id(user.getDocument_id());
                    userOutput.setName(user.getName());
                    userOutput.setUsername(user.getUsername());
                    userOutput.setPassword(user.getPassword());

                    String accountQuery = String.format("/.[user_id='%s']", user.getDocument_id());
                    userOutput.setAccounts(jsonDBTemplate.find(accountQuery, Account.class));

                    response.setCod(0);
                    response.setMsg("Usuario validado exitosamente.");
                    response.setData(userOutput);
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
        return Utilidades.generateResponse(response);
    }
    public String createUser(String stringUser) throws RemoteException {
        Response response = new Response();
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonDBTemplate jsonDBTemplate = JsonDB.getDB();

            User user = mapper.readValue(stringUser,User.class);

            if (user.getDocument_id() == null || user.getDocument_id().trim().equals("")){
                response.setCod(1);
                response.setMsg("Documento de identidad no puede estar vacio");
                return Utilidades.generateResponse(response);
            }
            if (user.getName() == null || user.getName().trim().equals("")){
                response.setCod(1);
                response.setMsg("Nombre no puede estar vacio");
                return Utilidades.generateResponse(response);
            }
            if (user.getUsername() == null || user.getUsername().trim().equals("")){
                response.setCod(1);
                response.setMsg("Usuario no puede estar vacio");
                return Utilidades.generateResponse(response);
            }
            if (user.getPassword() == null || user.getPassword().trim().equals("")){
                response.setCod(1);
                response.setMsg("Clave no puede estar vacio");
                return Utilidades.generateResponse(response);
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
        return Utilidades.generateResponse(response);
    }
    public String createAccount(String document_id, float montoInicial) throws RemoteException {
        Response response = new Response();
        try{
            JsonDBTemplate jsonDBTemplate = JsonDB.getDB();

            if (document_id == null || document_id.trim().equals("")){
                response.setCod(1);
                response.setMsg("Documento de identidad no puede estar vacio");
                return Utilidades.generateResponse(response);
            }
            if (montoInicial <= 0){
                response.setCod(1);
                response.setMsg("Monto incial no es válido");
                return Utilidades.generateResponse(response);
            }

            User user = jsonDBTemplate.findById(document_id, User.class);

            if (user != null){

                Account account = new Account(user.getDocument_id());

                //TODO - Realizar deposito inicial
                account.setCurrent_balance(montoInicial);

                jsonDBTemplate.insert(account);

                response.setCod(0);
                response.setMsg("Cuenta creada exitosamente.");
                response.setData(account);

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
        return Utilidades.generateResponse(response);
    }
    public String getAccount(int number) throws RemoteException {
        Response response = new Response();
        try{
            JsonDBTemplate jsonDBTemplate = JsonDB.getDB();

            if (number <= 0){
                response.setCod(1);
                response.setMsg("Numero de cuenta no es válido");
                return Utilidades.generateResponse(response);
            }

            Account account = jsonDBTemplate.findById(number, Account.class);

            if (account != null){

                String transactionQuery = String.format("/.[sourceNumber='%s' or destinationNumber='%s']", number, number);
                List<Transaction> transactions = jsonDBTemplate.find(transactionQuery, Transaction.class);

                AccountOutput output = new AccountOutput(account.getNumber(), account.getCurrent_balance(), account.getUser_id());
                output.setTransactions(transactions);

                response.setCod(0);
                response.setMsg("Cuenta creada exitosamente.");
                response.setData(output);

            }
            else{
                response.setCod(1);
                response.setMsg("Cuenta no existe.");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            response.setCod(99);
            response.setMsg("Ha ocurrido un error: "+e.getLocalizedMessage());
        }
        return Utilidades.generateResponse(response);
    }
}
