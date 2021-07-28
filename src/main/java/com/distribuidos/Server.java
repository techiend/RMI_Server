package com.distribuidos;

import com.distribuidos.service.AccountService;

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


            // Exporting the object of implementation class
            // (here we are exporting the remote object to the stub)
//            AccountService stub = accountImplementation;
//
//            Naming.rebind("//localhost:8808/account", stub);
//            System.out.println("Servidor activo...");

        }
        catch (Exception e) {
            System.err.println("Excepcion en servidor:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
