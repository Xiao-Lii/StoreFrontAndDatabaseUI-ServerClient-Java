package server;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer implements Runnable {
    protected int serverPort   = 10000;
    protected ServerSocket serverSocket = null;
    protected boolean isServerClosed = false;
    protected Thread runningThread = null;

    private static final int PORT = 10000;
    private static ExecutorService executorService = Executors.newFixedThreadPool(100);
    private static ArrayList<clientHandler> clients = new ArrayList<>();

    public MultiThreadServer(int port){
        this.serverPort = port;
    }

    /*
    public static void main (String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true){
            System.out.println("WAITING FOR CLIENT CONNECTION.");
            Socket client = serverSocket.accept();
            System.out.println("CONNECTED TO CLIENT.\n");
            clientHandler clientThread = new clientHandler(client);
            clients.add(clientThread);
            executorService.execute(clientThread);
        }
    }*/

    public void run(){
        // SETTING UP MAIN THREAD
        synchronized(this){ this.runningThread = Thread.currentThread(); }

        // OPENING THE SERVER SOCKET
        try { this.serverSocket = new ServerSocket(this.serverPort); } catch (IOException e) {
            throw new RuntimeException("Error: Failed to open port #: " + this.serverPort);
        }

        // WHILE THE SERVER ISN'T CLOSE - EXECUTE FOLLOWING EMBEDDED CODE
        // CREATE A NEW THREAD FOR EACH CLIENT CONNECTION - EACH HAVE THEIR OWN INPUT AND OUTPUT
        while(!isServerClosed()){
            Socket clientSocket = null;
            System.out.println("[SERVER] WAITING FOR CLIENT CONNECTION.");

            // ATTEMPT TO ACCEPT CLIENT CONNECTION
            try {
                clientSocket = this.serverSocket.accept();
                System.out.println("[SERVER] ACCEPTED CLIENT CONNECTION.\n");
            } catch (IOException e) {
                if(isServerClosed()) {
                    System.out.println("[SERVER] THE SERVER CONNECTION IS NOW CLOSED.") ;
                }
                throw new RuntimeException("[SERVER] ERROR ACCEPTING CLIENT CONNECTION");
            }

            // ATTEMPT TO CREATE NEW THREAD & START A CLIENT HANDLER
            try {
                new Thread(new clientHandler(clientSocket)).start();
                System.out.println("[SERVER] SUCCESSFULLY CREATED A NEW CLIENT THREAD.\n");
            } catch (IOException e) {
                System.err.println("[SERVER] ERROR THE THREAD COULD NOT BE OPENED FOR CLIENT.\n");
                e.printStackTrace();
            }
        }
        // SERVER SHUTDOWN - DONE WITH CLIENT CONNECTIONS
        System.out.println("[SERVER] THE SERVER CONNECTION WILL NOW CLOSED.\n") ;
    }

    private synchronized boolean isServerClosed() {
        return this.isServerClosed;
    }

    public synchronized void stop(){
        this.isServerClosed = true;
        try {
            this.serverSocket.close();
            System.out.println("[SERVER] THE SERVER CONNECTION WILL NOW CLOSED.\n") ;
        } catch (IOException e) {
            throw new RuntimeException("[SERVER] ERROR CLOSING SERVER.", e);
        }
    }

}
