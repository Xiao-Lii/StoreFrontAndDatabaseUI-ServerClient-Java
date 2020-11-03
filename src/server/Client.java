package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final int serverPort;
    private final String serverIP;
    private boolean isConnected;
    private Socket serverConnection;
    private PrintWriter output;
    private BufferedReader input;

    public Client(String ip, int port) {
        this.serverPort = port;
        this.serverIP = ip;
        this.isConnected = false;
    }

    public Client() {
        this("127.0.0.1", 10000);
    }

    public boolean isConnected() {
        return this.isConnected;
    }

    private PrintWriter getOutputStream() throws IOException {
        return new PrintWriter(this.serverConnection.getOutputStream(), true);
    }

    private BufferedReader getInputStream() throws IOException {
        return new BufferedReader(new InputStreamReader(this.serverConnection.getInputStream()));
    }

    public void connect() {
        displayMessage("Attempting to connect to server.");
        try {
            this.serverConnection = new Socket(this.serverIP, this.serverPort);
            this.isConnected = true;
            this.output = this.getOutputStream();
            this.input = this.getInputStream();

        } catch (IOException ioe) {
            this.input = null;
            this.output = null;
            this.serverConnection = null;
            this.isConnected = false;
        }
    }

    public void disconnect() {
        displayMessage("[CLIENT] TERMINATING CLIENT CONNECTION TO SERVER.");
        try {
            this.input.close();
            this.output.close();
            this.serverConnection.close();
        }
        catch (IOException | NullPointerException e) { e.printStackTrace(); }
    }


    // ************** !!!!!!!! LOOK AT ME YOU BLIND BAT !!!!!!!! **************
    // MAY NEED TO CONVERT THIS BACK TO STRING TO SEND MESSAGES BACK TO SERVER
    // ************** !!!!!!!! LOOK AT ME YOU BLIND BAT !!!!!!!! **************
    public void sendRequest(String request) throws IOException {
        this.output.println(request);
        displayMessage("[CLIENT] " + request);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    // TESTING CLIENT - DELETE MAIN LATER
    public static void main (String[] args){
        Client client = new Client();
        client.connect();
        try {
            client.sendRequest("SERVER YOU SUCK");
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            client.disconnect();
        }

    }
}
