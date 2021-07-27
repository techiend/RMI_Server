package com.carlosverde.implementation;

import com.carlosverde.model.Deposit;
import com.carlosverde.model.Transaction;
import com.carlosverde.model.Transference;
import com.carlosverde.model.Withdrawal;
import com.carlosverde.service.TransactionalService;

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
