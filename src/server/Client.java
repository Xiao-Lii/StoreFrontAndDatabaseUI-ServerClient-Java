package server;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final int SERVER_PORT = 10000;
    private static final String SERVER_IP = "127.0.0.1";
    private boolean isConnected = false;
    private Socket serverConnection;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private BufferedReader serverResponse;

    // TESTING CLIENT - DELETE MAIN LATER
    public static void main (String[] args) throws IOException {
        Client client = new Client();
        client.connect();

        while (client.isConnected()){
            String serverResponse = client.serverResponse.readLine();
            System.out.println("Server says:\n" + serverResponse);
        }
        client.disconnect();

    }

    public boolean isConnected() {
        return this.isConnected;
    }

    private ObjectOutputStream getObjectOutputStream() throws IOException{
        return new ObjectOutputStream(this.serverConnection.getOutputStream());
    }

    private ObjectInputStream getObjectInputStream() throws IOException{
        return new ObjectInputStream(this.serverConnection.getInputStream());
    }

    private BufferedReader getInputStream() throws IOException {
        return new BufferedReader(new InputStreamReader(this.serverConnection.getInputStream()));
    }

    public void connect() {
        displayMessage("[CLIENT] ATTEMPTING TO CONNECT TO SERVER.\n");
        try {
            this.serverConnection = new Socket(SERVER_IP, SERVER_PORT);
            this.isConnected = true;
            this.output = this.getObjectOutputStream();         // OBJECT OUTPUT STREAM MUST BE CREATED FIRST
            this.input = this.getObjectInputStream();           // ELSE ERRORS WILL OCCUR
            this.serverResponse = this.getInputStream();
            displayMessage("[CLIENT] CONNECTION TO SERVER SUCCESSFUL.\n");
        } catch (IOException ioe) {
            this.serverConnection = null;
            this.isConnected = false;
            this.output = null;
            this.input = null;
            this.serverResponse = null;
            displayMessage("[CLIENT] CONNECTION TO SERVER FAILED.\n");
        }
    }

    public void disconnect() {
        displayMessage("\n[CLIENT] TERMINATING CLIENT CONNECTION TO SERVER.\n");
        try {
            this.serverConnection.close();
            this.isConnected = false;
            this.output = null;
            this.input = null;
            this.serverResponse = null;
        }
        catch (IOException | NullPointerException e) { e.printStackTrace(); }
    }

    /*
    // ************** !!!!!!!! LOOK AT ME YOU BLIND BAT !!!!!!!! **************
    // MAY NEED TO CONVERT THIS BACK TO STRING TO SEND MESSAGES BACK TO SERVER
    // ************** !!!!!!!! LOOK AT ME YOU BLIND BAT !!!!!!!! **************
    public void sendRequest(String request) throws IOException {
        this.keyboardInput.println(request);
        displayMessage("[CLIENT] " + request);
    }
    */

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
