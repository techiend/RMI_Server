package com.carlosverde.implementation;

import com.carlosverde.model.Account;
import com.carlosverde.model.User;
import com.carlosverde.service.AccountService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AccountImplementation extends UnicastRemoteObject implements AccountService {
    public AccountImplementation() throws RemoteException{}
    public Account createAccount(User user) throws RemoteException {
        return null;
    }
    public Account getAccount(int number) throws RemoteException {
        return null;
    }
}
