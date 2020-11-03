package server;

import java.io.*;
import java.net.Socket;

public class clientHandler implements Runnable{
    private Socket clientSocket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String serverText;

    public clientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        //this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        //this.output = new PrintWriter(new PrintStream(clientSocket.getOutputStream()));
        this.serverText = null;
    }

    public clientHandler(Socket clientSocket, String serverText) throws IOException {
        this.clientSocket = clientSocket;
        //this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        //this.output = new PrintWriter(new PrintStream(clientSocket.getOutputStream()));
        this.serverText = serverText;
    }

    public void run() {
        try {
            this.output = new ObjectOutputStream(clientSocket.getOutputStream());
            this.input = new ObjectInputStream(clientSocket.getInputStream());
            //long time = System.currentTimeMillis();
            //output.write(("Client Handler: " + this.serverText + " - " + time + "").getBytes());
        }
        catch (IOException e) {
            // Reports the exception.
            System.err.println("IO Exception in client handler.");
            e.printStackTrace();
        }
        finally {
            try {
                if (output!=null)
                    output.close();
                if (input!=null)
                    input.close();
            } catch (IOException e) {
                System.err.println("ERROR: CLIENT HANDLER COULD NOT BE CLOSED.\n");
                e.printStackTrace();
            }
        }
    }
}
