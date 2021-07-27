package com.distribuidos.implementation;

import com.distribuidos.model.Deposit;
import com.distribuidos.model.Transaction;
import com.distribuidos.model.Transference;
import com.distribuidos.model.Withdrawal;
import com.distribuidos.service.TransactionalService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TransactionalImplementation extends UnicastRemoteObject implements TransactionalService {
    public TransactionalImplementation() throws RemoteException{}
    public Transaction doDeposit(Deposit deposit) throws RemoteException {
        return null;
    }
    public Transaction doWithdrawal(Withdrawal withdrawal) throws RemoteException {
        return null;
    }
    public Transaction doTransference(Transference transference) throws RemoteException {
        return null;
    }
}
