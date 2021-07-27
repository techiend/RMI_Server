package com.carlosverde.service;

import com.carlosverde.model.Account;
import com.carlosverde.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccountService extends Remote {
    Account createAccount(User user) throws RemoteException;
    Account getAccount(int number) throws RemoteException;
}
