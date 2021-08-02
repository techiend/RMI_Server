package com.distribuidos;

import com.distribuidos.datastorage.JsonDB;
import com.distribuidos.datastorage.Utilidades;
import com.distribuidos.inOutObjects.OpenAccountOutput;
import com.distribuidos.inOutObjects.Response;
import com.distribuidos.model.*;
import com.distribuidos.service.TransactionalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsondb.JsonDBTemplate;
import io.jsondb.query.Update;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransactionalImplementation extends UnicastRemoteObject implements TransactionalService {
    TransactionalImplementation() throws RemoteException{}
    public Integer doDeposit(int destino, float monto, String descrip) throws RemoteException {

        JsonDBTemplate jsonDBTemplate = JsonDB.getDB();

        Account account = jsonDBTemplate.findById(destino, Account.class);

        float actual = 0;

        if (account == null){
            return 0;
        }
        else{
            actual = account.getCurrent_balance(); // 7000
            float montoFinal = monto + actual;

            Update update = Update.update("current_balance",montoFinal);

            String jxQuery = String.format("/.[number=%d]", destino);
            Account finalAccount = jsonDBTemplate.findAndModify(jxQuery, update, Account.class);

            Deposit deposit = new Deposit(monto,descrip, 0, destino);
//            deposit.setAmount(monto);
//            deposit.setDate(new Date());
//            deposit.setDesc(descrip);
//            deposit.setDestinationNumber(destino);
//            deposit.setSourceNumber(0);
//            deposit.setType("Deposit");

            jsonDBTemplate.insert(deposit);

            return 1;
        }
    }
    public String doWithdrawal(float amount, int cuentaOrigen) throws RemoteException {
        Response response = new Response();
        try{
            JsonDBTemplate jsonDBTemplate = JsonDB.getDB();

            if (amount <= 0){
                response.setCod(1);
                response.setMsg("El monto introducido no es válido.");
                return Utilidades.generateResponse(response);
            }
            if (cuentaOrigen <= 0){
                response.setCod(1);
                response.setMsg("La cuenta origen no es válida.");
                return Utilidades.generateResponse(response);
            }


            Account findAccount = jsonDBTemplate.findById(cuentaOrigen, Account.class);

            if (findAccount != null){

                float actual = findAccount.getCurrent_balance();

                if (amount <= actual) {

                    float montoFinal = actual - amount;

                    Update update = Update.update("current_balance",montoFinal);

                    String jxQuery = String.format("/.[number=%d]", cuentaOrigen);
                    Account finalAccount = jsonDBTemplate.findAndModify(jxQuery, update, Account.class);

                    Withdrawal retiro = new Withdrawal(amount,"Retiro de cuenta",cuentaOrigen,0);

                    jsonDBTemplate.insert(retiro);

                    response.setCod(0);
                    response.setMsg("Retiro realizado correctamente.");
                    response.setData(montoFinal);
                }
                else {
                    response.setCod(1);
                    response.setMsg("Fondos insuficientes.");
                }

            }
            else{
                response.setCod(1);
                response.setMsg("La cuenta seleccionada no existe.");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            response.setCod(99);
            response.setMsg("Ha ocurrido un error: "+e.getLocalizedMessage());
        }
        return Utilidades.generateResponse(response);
    }
    public Integer doTransference(  float amount, String description, int originAccount, int destinationAccount ) throws RemoteException {
    	
    	JsonDBTemplate jsonDBTemplate = JsonDB.getDB();
    	
    	Account accountOrigin = jsonDBTemplate.findById( originAccount, Account.class);
    	Account accountDestination = jsonDBTemplate.findById( destinationAccount, Account.class);
    	
    	if (accountOrigin == null || accountDestination == null ){
            return 0;
        } else if (accountOrigin.getCurrent_balance() < amount) {
        	return -1;
        } else {
        	float newBalanceOrigin = accountOrigin.getCurrent_balance() - amount;
        	float newBalanceDestination = accountDestination.getCurrent_balance() + amount;
        	
        	Update updateOrigin = Update.update( "current_balance", newBalanceOrigin );
        	Update updateDestination = Update.update( "current_balance", newBalanceDestination );
        	
        	String jxQueryOrigin = String.format("/.[number=%d]", originAccount );
        	String jxQueryDestination = String.format("/.[number=%d]", destinationAccount );
        	
            Account finalAccountOrigin = jsonDBTemplate.findAndModify(jxQueryOrigin, updateOrigin, Account.class);
            Account finalAccountDestination = jsonDBTemplate.findAndModify(jxQueryDestination, updateDestination, Account.class);
            
            Transference transference = new Transference( amount, description, originAccount, destinationAccount );
            
            jsonDBTemplate.insert( transference );
        	
        	return 1;
        }

    }
}
