import Users.StoreSystem;
import server.Client;
import server.MultiThreadServer;

import java.io.IOException;

public class tempMain {
    public static void main(String[] args) {
        StoreSystem storeSystem = new StoreSystem();
        storeSystem.createUser( "email@email.com", "pw", "defaultUser");
        //storeSystem.createUser( "email@email.com", "pw", "defaultUser");

        MultiThreadServer server = new MultiThreadServer(10000);
        new Thread(server).start();

        Client client = new Client();
        client.connect();
        try {
            client.sendRequest("SERVER YOU SUCK");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("Stopping Server");
        //server.stop();

    }
}           // END OF MAIN


