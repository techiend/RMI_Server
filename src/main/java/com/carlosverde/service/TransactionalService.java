package com.carlosverde.service;

import com.carlosverde.model.Deposit;
import com.carlosverde.model.Transaction;
import com.carlosverde.model.Transference;
import com.carlosverde.model.Withdrawal;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TransactionalService extends Remote {
    Transaction doDeposit(Deposit deposit) throws RemoteException;
    Transaction doWithdrawal(Withdrawal withdrawal) throws RemoteException;
    Transaction doTransference(Transference transference) throws RemoteException;
}
