package com.distribuidos.service;

import com.distribuidos.inOutObjects.Response;
import com.distribuidos.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccountService extends Remote {
    Response checkUser(String document_id) throws RemoteException;
    Response validateUser(String username, String password) throws RemoteException;
    Response createAccount(User user) throws RemoteException;
    Response getAccount(int number) throws RemoteException;
}
