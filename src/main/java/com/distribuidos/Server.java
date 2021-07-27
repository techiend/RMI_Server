package com.distribuidos;

import com.distribuidos.implementation.AccountImplementation;
import com.distribuidos.implementation.TransactionalImplementation;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {

            System.setProperty("java.rmi.server.hostname", "localhost");
            LocateRegistry.createRegistry(8808);
            TransactionalImplementation transactionalImplementation = new TransactionalImplementation();
            AccountImplementation accountImplementation = new AccountImplementation();
            Naming.rebind("//localhost:8808/transactional", transactionalImplementation);
            Naming.rebind("//localhost:8808/account", accountImplementation);
            System.out.println("Servidor activo...");

        }
        catch (Exception e) {
            System.err.println("Excepcion en servidor:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
