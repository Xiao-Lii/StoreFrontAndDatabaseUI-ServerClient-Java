package server;

import java.io.*;
import java.net.Socket;

public class clientHandler implements Runnable{
    private Socket clientSocket = null;
    private BufferedReader input;
    private PrintWriter output;

    public clientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.output = new PrintWriter(new PrintStream(clientSocket.getOutputStream()));
    }

    public void run() {
        try {
            while (true){
                output.write("PROCESS HAS BEEN COMPLETED.");
            }

        } /*
        catch (IOException e) {
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
