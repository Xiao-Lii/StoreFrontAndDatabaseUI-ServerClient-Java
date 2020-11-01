package server;

import Users.User;

import java.io.*;
import java.net.Socket;

public class clientHandler implements Runnable{
    private Socket clientSocket = null;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    User user;

    public clientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.input = new ObjectInputStream(clientSocket.getInputStream());
        this.output = new ObjectOutputStream(clientSocket.getOutputStream());
    }

    public void run() {
        try {
            while (true){
                //output.writeObject(this.user);
            }

        } /*catch (IOException e) {
            // Reports the exception.
            System.err.println("IO Exception in client handler.");
            e.printStackTrace();
        }*/
        finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                System.err.println("ERROR: CLIENT HANDLER COULD NOT BE CLOSED.\n");
                e.printStackTrace();
            }
        }
    }
}
