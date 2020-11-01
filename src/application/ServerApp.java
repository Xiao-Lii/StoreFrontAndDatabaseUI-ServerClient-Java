package application;

import server.Client;
import server.MultiThreadServer;

import java.util.*;

public class ServerApp {

    public static void main(String[] args) {
        //StoreSystem storeSystem = new StoreSystem();
        //storeSystem.createUser( "email@email.com", "pw", "defaultUser");
        //storeSystem.createUser( "email@email.com", "pw", "defaultUser");
        Scanner input = new Scanner(System.in);

        // FIRST LETS DISPLAY OUR TWO OPTIONS TO THE USER
        // A. LOAD DATA FROM A FILE
        // B. START THE SERVER
        System.out.println("Server Main Menu\n" +
                "1. Load data from file\n" +
                "2. Start the Server\n");

        int userInput = input.nextInt();
        switch (userInput){
            case 1:

                break;
            case 2:
                // OPTION B: STARTING OUR MULTI-THREAD SERVER
                MultiThreadServer server = new MultiThreadServer(10000);
                new Thread(server).start();

                Client mainConnection = new Client();
                mainConnection.connect();
                while (mainConnection.isConnected()) {

                }
                break;
            default:

                break;
        }


    }       // END OF MAIN
}           // END OF SERVER APP CLASS
