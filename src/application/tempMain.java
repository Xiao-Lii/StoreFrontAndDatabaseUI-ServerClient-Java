package application;

import Users.StoreSystem;
import server.Client;
import server.MultiThreadServer;
import java.io.IOException;
import java.io.PrintWriter;

public class tempMain {
    public static void main (String[] args){
        StoreSystem storeSystem = new StoreSystem();
        storeSystem.createUser( "email@email.com", "pw", "defaultUser");
        //storeSystem.createUser( "email@email.com", "pw", "defaultUser");

        MultiThreadServer server = new MultiThreadServer(10000);
        new Thread(server).start();

        boolean run = server.isServerClosed();

        while(!run) {

        }
        System.out.println("Stopping Server");
        server.stop();
    }
}
