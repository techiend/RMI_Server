package com.distribuidos.service;

import com.distribuidos.model.Account;
import com.distribuidos.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccountService extends Remote {
    Account createAccount(User user) throws RemoteException;
    Account getAccount(int number) throws RemoteException;
}
