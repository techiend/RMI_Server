package com.distribuidos;

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

            art();

        }
        catch (Exception e) {
            System.err.println("Excepcion en servidor:");
            e.printStackTrace();
            System.exit(1);
        }
    }



    public static void art(){
        System.out.println("   __  __ _       _   ____              _       _____                            ");
        System.out.println("  |  \\/  (_)     (_) |  _ \\            | |     / ____|                           ");
        System.out.println("  | \\  / |_ _ __  _  | |_) | __ _ _ __ | | __ | (___   ___ _ ____   _____ _ __   ");
        System.out.println("  | |\\/| | | '_ \\| | |  _ < / _` | '_ \\| |/ /  \\___ \\ / _ \\ '__\\ \\ / / _ \\ '__|  ");
        System.out.println("  | |  | | | | | | | | |_) | (_| | | | |   <   ____) |  __/ |   \\ V /  __/ |     ");
        System.out.println("  |_|  |_|_|_| |_|_| |____/ \\__,_|_| |_|_|\\_\\ |_____/ \\___|_|    \\_/ \\___|_|     ");
        System.out.println("                                                                                 ");

        System.out.println("");
    }

}
