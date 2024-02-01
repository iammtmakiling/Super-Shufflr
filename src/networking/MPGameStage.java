package networking;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import game.Element;
import game.MPUser;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Platform;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MPGameStage{
    private Socket socket;
    private static PrintWriter writer;
	private Scene scene;
	private static Stage stage;
	private String stringInput;
	private MPUser user;
	private GridPane mainPane;
	private ArrayList<Element> tiles;
	private ArrayList<Character> shuffledWordArray;
	private Group root;
	private Canvas canvas;
	static GraphicsContext gc;
	private VBox chatPane;
	private TextField input;
	private TextArea displayAllMessages;
	private ScrollPane scrollPane;
	private static Dictionary<String, Integer> players;
	public final static int WINDOW_WIDTH = 1000;
	public final static int WINDOW_HEIGHT = 600;

	public final static int MAP_NUM_ROWS = 1;
	public static int MAP_NUM_COLS = 0;
	public final static int MAP_WIDTH = 500;
	public final static int MAP_HEIGHT = 500;
	public final static int CELL_WIDTH = 65;
	public final static int CELL_HEIGHT = 65;
	public final static boolean IS_GAME_DONE = false;
	private static boolean FLAG_START = false;
	private int count = 0;
	private static String shuffledWord;
	private static String answerWord;
	protected MPGameStage gameStage;
	private String userName;

	private boolean flagStart = false;

	private final Image ICON = new Image ("images/icon.png",500,500,false,false);
	public final Image bg = new Image("images/bg.png",WINDOW_WIDTH,WINDOW_HEIGHT,false,false);
	public final Image bg1 = new Image("images/3waiting.png",WINDOW_WIDTH,WINDOW_HEIGHT,false,false);
	public final Image bg2 = new Image("images/2waiting.png",WINDOW_WIDTH,WINDOW_HEIGHT,false,false);
	public final Image bg3 = new Image("images/1waiting.png",WINDOW_WIDTH,WINDOW_HEIGHT,false,false);
	public final Image bg4 = new Image("images/MPBackground.png",WINDOW_WIDTH,WINDOW_HEIGHT,false,false);
	private GameTimer gametimer;
	
	private String host;
	private int port;
	
	
	/**
	 * Constructor for MPGameStage class.
	 * Initializes the game stage and connects to the server.
	 */
//    @Override
	public MPGameStage(String userName, int port, String host)  {
		System.out.println("Host: " + host);
		this.port = port;
		this.host = host;
		
		this.gameStage = gameStage;
		players = new Hashtable<String, Integer>();
		players.put(userName, 0);
		this.user = new MPUser("player");
		this.userName = userName;
		this.root = new Group();
		this.scene = new Scene(root, WINDOW_WIDTH,WINDOW_HEIGHT,Color.WHITE);
		this.canvas = new Canvas(WINDOW_WIDTH,WINDOW_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		this.chatPane = new VBox();
		this.input = new TextField();
		this.stringInput = "";
		this.displayAllMessages = new TextArea();
        this.scrollPane = new ScrollPane();
		this.mainPane = new GridPane();
		this.tiles = new ArrayList<Element>();
		this.shuffledWordArray = new ArrayList<Character>();
		
        connectToServer();
	}
	

	//method to add the stage elements
	public void setStage(Stage stage) {
		this.stage = stage;
        stage.getIcons().add(ICON);
        gc.drawImage( bg, 0, 0);
        
        //set stage elements here
		this.root.getChildren().add(canvas);
		this.root.getChildren().add(chatPane);
		this.root.getChildren().add(mainPane);
		stage.setTitle("Super Shuffler");
		stage.setScene(this.scene);
		stage.show();

        stage.setOnCloseRequest(e -> closeConnection());
	}

	//Create the visual elements of the game based on the shuffled word.
	private void createVisuals(){
        tiles.removeAll(tiles);
        shuffledWordArray.removeAll(shuffledWordArray);
       
		MAP_NUM_COLS = shuffledWord.length();
		
        for (int i = 0; i < shuffledWord.length(); i++) {
        	 shuffledWordArray.add(shuffledWord.charAt(i));
        }

			for(int j=0;j<MAP_NUM_COLS;j++){
				Element tile = null;
				switch (shuffledWordArray.get(j)){
					case 'a':
						tile = new Element(Element.A_TYPE,this);
						break;
					case 'b':
						tile = new Element(Element.B_TYPE,this);
						break;
					case 'c':
						tile = new Element(Element.C_TYPE,this);
						break;
					case 'd':
						tile = new Element(Element.D_TYPE,this);
						break;
					case 'e':
						tile = new Element(Element.E_TYPE,this);
						break;
					case 'f':
						tile = new Element(Element.F_TYPE,this);
						break;
					case 'g':
						tile = new Element(Element.G_TYPE,this);
						break;
					case 'h':
						tile = new Element(Element.H_TYPE,this);
						break;
					case 'i':
						tile = new Element(Element.I_TYPE,this);
						break;
					case 'j':
						tile = new Element(Element.J_TYPE,this);
						break;
					case 'k':
						tile = new Element(Element.K_TYPE,this);
						break;
					case 'l':
						tile = new Element(Element.L_TYPE,this);
						break;
					case 'm':
						tile = new Element(Element.M_TYPE,this);
						break;
					case 'n':
						tile = new Element(Element.N_TYPE,this);
						break;
					case 'o':
						tile = new Element(Element.O_TYPE,this);
						break;
					case 'p':
						tile = new Element(Element.P_TYPE,this);
						break;
					case 'q':
						tile = new Element(Element.Q_TYPE,this);
						break;
					case 'r':
						tile = new Element(Element.R_TYPE,this);
						break;
					case 's':
						tile = new Element(Element.S_TYPE,this);
						break;
					case 't':
						tile = new Element(Element.T_TYPE,this);
						break;
					case 'u':
						tile = new Element(Element.U_TYPE,this);
						break;
					case 'v':
						tile = new Element(Element.V_TYPE,this);
						break;
					case 'w':
						tile = new Element(Element.W_TYPE,this);
						break;
					case 'x':
						tile = new Element(Element.X_TYPE,this);
						break;
					case 'y':
						tile = new Element(Element.Y_TYPE,this);
						break;
					case 'z':
						tile = new Element(Element.Z_TYPE,this);
						break;
				}
				this.tiles.add(tile);
			}

		if (flagStart == false) {
			flagStart = true;
			this.setChatPanePropoerties();
		}
		this.setMainPaneProperties();
		this.setMainPaneContents();
	}

	//Set the game over state and transition to the game over stage.
	static void setGameOver(Dictionary<String, Integer> statBoard){
		PauseTransition transition = new PauseTransition(Duration.seconds(1));
		transition.play();

		transition.setOnFinished(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				try {
					writer.println("GAME ENDED");
				} catch (Exception e) {
					e.printStackTrace();
				}
				GameOverStage gameover = new GameOverStage();
				gameover.setGameover(statBoard);
				stage.setScene(gameover.getScene());
			}
		});
	}

	//Remove all children from the main pane.
	private void removeChildren() {
		mainPane.getChildren().clear();
	}

	private void setMainPaneContents(){

		 //loop that will set the constraints of the elements in the grid pane
	     int counter=0;
	     for(int row=0;row<MAP_NUM_ROWS;row++){
	    	 for(int col=0;col<MAP_NUM_COLS;col++){
	    		 GridPane.setConstraints(tiles.get(counter).getImageView(),col,row);
	    		 counter++;
	    	 }
	     }

	     for(Element tile: tiles){
	    	 this.mainPane.getChildren().add(tile.getImageView());
	     }
	}

	//Set the properties and event handling for the chat pane.
	private void setChatPanePropoerties(){
		this.chatPane.setLayoutX(WINDOW_WIDTH*0.66);
	    this.chatPane.setLayoutY(WINDOW_HEIGHT*0.03);
	    this.chatPane.getChildren().add(displayAllMessages);
	    this.chatPane.getChildren().add(input);

	    this.displayAllMessages.setStyle("-fx-focus-color: transparent; -fx-text-box-border: transparent;");
        this.displayAllMessages.setPrefHeight(WINDOW_HEIGHT-(WINDOW_HEIGHT/10));
        this.displayAllMessages.setEditable(false);
        this.scrollPane.setContent(displayAllMessages);
        this.scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        this.displayAllMessages.setPrefWidth(WINDOW_WIDTH/3);
		this.input.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                stringInput = input.getText();
                this.user.setAnswer(stringInput);
                this.input.setText("");
                System.out.println(answerWord);           
                if (answerWord.equals(stringInput)) {
                	int score = this.user.givePoints(answerWord.length());
                	this.removeChildren();
                	this.displayAllMessages.appendText(userName + ": " +stringInput+ " ++" + score +" POINTS\n" ); // CORRECT
					players.put(userName, players.get(userName) + score);
                    String toServer = userName + ": " + stringInput + " ++" + score +" POINTS";
                    writer.println(toServer);
                	writer.println("Answered&:&"+userName+"&:&"+score);
                } else {
                	this.displayAllMessages.appendText(userName + ": " +stringInput + "\n");
                    String toServer = userName + ": " + stringInput;
                    writer.println(toServer);
                }
                System.out.println("user points: "+ user.getPoints());
                System.out.println("answer: " + answerWord);
            }
        });
	}

	private void setMainPaneProperties(){
	    this.mainPane.setHgap(5);

	    switch (MAP_NUM_COLS) {
	    	case 4:
	    		this.mainPane.setLayoutX(WINDOW_WIDTH*0.2);
	            break;
	    	case 5:
	    		this.mainPane.setLayoutX(WINDOW_WIDTH*0.16);
	            break;
	    	case 6:
	    		this.mainPane.setLayoutX(WINDOW_WIDTH*0.12);
	            break;
	    	case 7:
	    		this.mainPane.setLayoutX(WINDOW_WIDTH*0.08);
	            break;
	    	case 8:
	    		this.mainPane.setLayoutX(WINDOW_WIDTH*0.05);
	            break;
	    	case 9:
	    		this.mainPane.setLayoutX(WINDOW_WIDTH*0.02);
	            break;
	    }
	    this.mainPane.setLayoutY(WINDOW_HEIGHT*0.55);
	}
	
	Stage getStage() {
		return stage;
	}
	
	public Scene getScene() {
		return this.scene;
	}


//Connect to the server and create a server handler thread.
    private void connectToServer() {
        try {
            socket = new Socket(this.host, this.port);
            System.out.println("Connected to the server.");

            writer = new PrintWriter(socket.getOutputStream(), true);

            Thread serverThread = new Thread(new ServerHandler(socket));
            serverThread.start();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Close the socket connection and the writer.
    private void closeConnection() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	protected static Dictionary<String, Integer> getPlayersInfo(){
		return players;
	}

    //Initialize the socket and reader in the ServerHandler class constructor.
    class ServerHandler implements Runnable {
        private Socket socket;
        private Scanner reader;

        public ServerHandler(Socket socket) {
            try {
                this.socket = socket;
                reader = new Scanner(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Handle messages received from the server and perform corresponding actions.
        @Override
        public void run() {
            try {
                while (true) {
					if (reader.hasNextLine()) {
						String message = reader.nextLine();
						// Handle messages from the server
						if (message.startsWith("StartGame")){
							System.out.println("STARTING GAME");
							FLAG_START = true;
							Platform.runLater(() -> {
								//instantiate an animation timer
								gc.drawImage( bg4, 0, 0);
								gametimer = new GameTimer(gc,scene, gameStage);
								new Thread (gametimer).start();
							});
						}  else if (message.startsWith("ConnectedClientsCount:") && FLAG_START==false) {
							count = Integer.parseInt(message.substring("ConnectedClientsCount:".length()));
							// Update the UI or perform any necessary actions with the count
							System.out.println("Connected clients count: " + count);
							System.out.println("Server.CLIENT_COUNT - count:"+(Server.CLIENT_COUNT - count));
							if (Server.CLIENT_COUNT - count == 3) {
								Platform.runLater(() -> {
									gc.drawImage( bg1, 0, 0);
								});                 	  
							} else if (Server.CLIENT_COUNT - count == 2) {
								Platform.runLater(() -> {
									gc.drawImage( bg2, 0, 0);
								});                 	  
							}
							else if (Server.CLIENT_COUNT - count == 1) {
								Platform.runLater(() -> {
									gc.drawImage( bg3, 0, 0);
								});                 	  
							} else if (Server.CLIENT_COUNT - count == 0){
								System.out.println("SENDING CONNECTIONS");
								String toServer ="Connection:" + userName;
								writer.println(toServer);
								gc.drawImage( bg, 0, 0); // TO DO: STARTING GAME PIC PLS
							}
						} else if (message.startsWith("Connection:")) { 
							System.out.println("FROM SERVER"+message);
							String playerName = message.substring("Connection:".length());
							if (players.get(playerName) == null){
								players.put(playerName, 0);
							}

						}else if (FLAG_START== true && message.startsWith("Answered" )){
							String[] list = message.split("&:&");
							players.put(list[1],  (players.get(list[1]) + Integer.parseInt(list[2])));
						
						}else if (FLAG_START== true && message.startsWith("Guess the Word:" )){
							String word = message.substring("Guess the Word:".length());
							// Handle the received word
							Platform.runLater(() -> {
								// Do something with the word
								removeChildren();
								shuffledWord = word;
								System.out.println("Received word: " + word);
								GameTimer.resetGameCounter();
								createVisuals();
							});
						}
						else if (FLAG_START== true && message.startsWith("Answer:" )){
							String word = message.substring("Answer:".length());
							// Handle the received word
							Platform.runLater(() -> {
								// Do something with the word
								answerWord = word;
								System.out.println("Answer: " + word);
							});
						}
						else {
							Platform.runLater(() -> {
								displayAllMessages.appendText(message + "\n");
								displayAllMessages.positionCaret(displayAllMessages.getLength());
							});  
						}
					}
                }
            } finally {
                reader.close();
            }
        }
    }
}

