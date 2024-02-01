package networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import game.WordBank;
import javafx.stage.Stage;
import networking.Server;

/**
 * The Server class represents the server component of the network application.
 * It handles client connections and communication
 */
public class Server {
    private int port;
    static final int CLIENT_COUNT = 4;
    private static List<PrintWriter> clients = new ArrayList<>();
    private static int clientCount = 0;
    private static boolean FLAG_START = false;
    private static Stage stage;
    private static List<String> players =new ArrayList<String>();
    private WordBank wordBank;
    ServerSocket serverSocket;

    /**
     * Constructor for the Server class. Starts the server and listens for client connections.
     */
    public Server(String host,int port) {
        this.port = port;
        this.wordBank = new WordBank();
        try {
            serverSocket = new ServerSocket(this.port);
            System.out.println("Server started on port " + this.port);
            
            while (true) {
                if (clientCount < CLIENT_COUNT) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("New client connected: " + clientSocket);

                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                    clients.add(writer);
                    clientCount++;
                    System.out.println("Client Count: " + clientCount);
                    
                    // Send the connected clients count to all clients
                    sendConnectedClientsCount();
                    
                    Thread clientThread = new Thread(new ClientHandler(clientSocket, writer, this));
                    clientThread.start();
                }
                if(!FLAG_START){
                    System.out.println(players.size());
                }
                if (clientCount == CLIENT_COUNT && !FLAG_START && players.size() == CLIENT_COUNT) {
                    System.out.println("SENDING CLIENT NAMES");
                    sendConnectedClientsNames();
                    FLAG_START = true;
                    generateWord();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Generates a new word from the WordBank and sends it to all connected clients.
     */
    void generateWord() {
        this.wordBank.randomizeWord();
        for (PrintWriter writer : clients) {
            writer.println("Guess the Word:" + this.wordBank.getShuffled());
            writer.println("Answer:" + this.wordBank.getAnswer());
        }
    }
    // private static void sendConnectedClientsStart() {
    //     for (PrintWriter writer : clients) {
    //         writer.println("StartGame");
    //     }
    // }

    /**
     * Sends the current count of connected clients to all clients.
     */
    private static void sendConnectedClientsCount() {
        for (PrintWriter writer : clients) {
            writer.println("ConnectedClientsCount:" + clientCount);
        }
    }
    
    private static void sendConnectedClientsNames() {
        for (PrintWriter writer : clients) {
            System.out.println("CLIENT: "+writer);
            for (String playerName : players){
                System.out.println(players);
                writer.println("Connection:" + playerName);
            }
        }
        System.out.println("STARTING GAME");
        for (PrintWriter writer : clients) {
            writer.println("StartGame");
        }
    }

    /**
     * Broadcasts a message to all connected clients except the sender.
     */
    public static void broadcastMessage(String message, PrintWriter sender) {
        for (PrintWriter client : clients) {
            if (client != sender) {
                client.println(message);
            }
        }
    }

    /**
     * Returns the current count of connected clients.
     * 
     * @return The count of connected clients
     */
    protected static int getClientsCounts() {
        return clientCount;
    }

    protected void addPlayer(String playerName){
        players.add(playerName);
    }
    protected int getSize(){
        return players.size();
    }
    
    protected void gameEnded() {
    	try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}

/**
 * The ClientHandler class represents a thread that handles communication with a connected client.
 * It listens for messages from the client.
 */
class ClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter writer;
    private Scanner reader;
    private Server server;
    
    public ClientHandler(Socket socket, PrintWriter writer, Server server) {
        try {
            clientSocket = socket;
            this.writer = writer;
            this.server = server;
            reader = new Scanner(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
     * If the message starts with "Answered", generate a new word
     * else, print the message received
    */
    @Override
    public void run() {
        try {
            while (true) {
                if (reader.hasNextLine()) {
                    String message = reader.nextLine();
                    if (message.startsWith("Answered")) {
                        Server.broadcastMessage(message, writer);
                        this.server.generateWord();
                    } else if (message.startsWith("Connection:")) {
                        System.out.println(message);
                        this.server.addPlayer(message.substring("Connection:".length()));                   
                        System.out.println("PLAYER SIZE: "+this.server.getSize());
                    } else if (message.startsWith("GAME ENDED")) {
                        System.out.println(message);
                        server.gameEnded();
                        break;
                    }
                    else {                   
                        System.out.println("Received message: " + message);
                        Server.broadcastMessage(message, writer);
                    }
                }
            }
        } finally {
            reader.close();
        }
    }
}
