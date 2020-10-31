package server;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class MultiThreadServer implements Runnable {
    protected int          serverPort   = 10000;
    protected ServerSocket serverSocket = null;
    protected boolean isServerClosed = false;
    protected Thread       runningThread= null;

    public MultiThreadServer(int port){
        this.serverPort = port;
    }

    public void run(){
        // SETTING UP MAIN THREAD
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }

        // OPENING THE SERVER SOCKET
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Error: Failed to open port #: " + this.serverPort, e);  // MAY NEED TO EDIT THIS
        }

        // WHILE THE SERVER ISN'T CLOSE - EXECUTE FOLLOWING EMBEDDED CODE
        // CREATE A NEW THREAD FOR EACH CLIENT CONNECTION - EACH HAVE THEIR OWN INPUT AND OUTPUT
        while(!isServerClosed()){
            Socket clientSocket = null;

            // ATTEMPT TO ACCEPT CLIENT CONNECTION
            try {clientSocket = this.serverSocket.accept(); } catch (IOException e) {
                if(isServerClosed()) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            // CREATES NEW THREAD & STARTS CLIENT WORKER
            new Thread(new clientWorker(clientSocket, "Multithreaded Server")).start();
        }
        // SERVER SHUTDOWN - DONE WITH CLIENT CONNECTIONS
        System.out.println("The server will now shut down.\n") ;
    }


    private synchronized boolean isServerClosed() {
        return this.isServerClosed;
    }

    public synchronized void stop(){
        this.isServerClosed = true;
        try { this.serverSocket.close(); } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

}
