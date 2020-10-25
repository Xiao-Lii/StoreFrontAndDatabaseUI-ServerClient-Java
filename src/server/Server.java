package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server implements Runnable {
    private final int port, backlog;
    private int connectionCounter;
    private ServerSocket serverSocket;
    private boolean keepServerRunning;

    public Server(int port, int backlog) {
        this.port = port;
        this.backlog = backlog;
        this.connectionCounter = 0;
        this.keepServerRunning = true;
    }

    public Server() {
        this.port = 10000;
        this.backlog = 100;
        this.connectionCounter = 0;
        this.keepServerRunning = true;

    }

    private Socket waitForClientConnection() throws IOException {
        System.out.println("Waiting for a connection...\n");
        Socket clientConnection = serverSocket.accept();
        this.connectionCounter++;
        System.out.printf("Connection #%d accepted from %s %n", this.connectionCounter +
                clientConnection.getInetAddress().getHostName());
        return clientConnection;
    }

    private PrintWriter getOutputStream(Socket clientConnection) throws IOException {
        return new PrintWriter(clientConnection.getOutputStream(), true);
    }

    private BufferedReader getInputStream(Socket clientConnection) throws IOException {
        return new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
    }

    private void sendMessage(PrintWriter output, String message) {
        output.println("Connected to the server.");
    }

    private void processClientRequest(BufferedReader input) throws IOException {
        String clientMessage = input.readLine();
        System.out.println("CLIENT SAID>>>" + clientMessage);
    }

    private void closeClientConnection(Socket clientConnection, BufferedReader input, PrintWriter output) {
        // Closing input, output, and client connection.
        try {
            input.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        try {
            output.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        try {
            clientConnection.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(this.port, this.backlog);
            Socket clientConnection = null;
            PrintWriter output = null;
            BufferedReader input = null;

            while (true) {
                try {
                    clientConnection = this.waitForClientConnection();

                    System.out.println("Getting Data Streams\n");
                    output = getOutputStream(clientConnection);
                    input = getInputStream(clientConnection);

                    // Processing the Requests and sending the responses.
                    sendMessage(output, "Connected to the server.\n");
                    processClientRequest(input);

                    Thread.sleep(100);

                    output.println("Connected to simple Java Server!\n");       // Send to Client

                    String clientMessage = input.readLine();
                    System.out.println("CLIENT SAID >>>> " + clientMessage);    // Receive from Client
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } finally {
                    closeClientConnection(clientConnection, input, output);
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: Cannot open the server.\n");
            e.printStackTrace();
        }
    }
}




