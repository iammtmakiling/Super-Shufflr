package soloGame;

import java.util.ArrayList;

import game.Element;
import game.WordBank;
import game.User;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import soloGame.GameStage;


public class GameStage {
	private Scene scene;
	private static Stage stage;
	private String stringInput;
	private User user;
	private WordBank wordBank;
	
	private GridPane mainPane;
	private ArrayList<Element> tiles;
	private ArrayList<Character> shuffledWordArray;

	private Group root;
	private Canvas canvas;
	static GraphicsContext gc;
	private VBox chatPane;
//	private Text textScore;
//	private Text textTries;
	private TextField input;
	private TextArea displayAllMessages;
	private ScrollPane scrollPane;
	public final static int WINDOW_WIDTH = 1000;
	public final static int WINDOW_HEIGHT = 600;

	public final static int MAP_NUM_ROWS = 1;
	public static int MAP_NUM_COLS = 0;
	public final static int MAP_WIDTH = 500;
	public final static int MAP_HEIGHT = 500;
	public final static int CELL_WIDTH = 65;
	public final static int CELL_HEIGHT = 65;
	public final static boolean IS_GAME_DONE = false;

	private boolean flagStart = false;

	private final Image ICON = new Image ("images/icon.png",500,500,false,false);
	public final Image bg = new Image("images/SoloBackground.png",WINDOW_WIDTH,WINDOW_HEIGHT,false,false);
	private soloGame.GameTimer gametimer;

	public GameStage(String userName) {
		this.user = new User(userName);
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,Color.WHITE);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		this.chatPane = new VBox();
		this.input = new TextField();
//		this.textScore = new Text(10,70,"SCORE: " + this.user.getPoints());
//		this.textTries = new Text(170,70, "LIVES LEFT: " + this.user.getLives());
		this.stringInput = "";
		this.displayAllMessages = new TextArea();
        this.scrollPane = new ScrollPane();
        this.wordBank = new WordBank();
		this.wordBank.randomizeWord();
		this.mainPane = new GridPane();
		this.tiles = new ArrayList<Element>();
		this.shuffledWordArray = new ArrayList<Character>();
		
		//instantiate an animation timer
		this.gametimer = new GameTimer(GameStage.gc,this.scene, this);
		new Thread (gametimer).start();
	}

	//method to add the stage elements
	public void setStage(Stage stage) {
		this.stage = stage;
        stage.getIcons().add(ICON);

        //set stage elements here
		this.root.getChildren().add(canvas);
		this.root.getChildren().add(chatPane);
		this.root.getChildren().add(mainPane);
		gc.drawImage( this.bg, 0, 0);
		stage.setTitle("Super Shuffler");
		stage.setScene(this.scene);
		stage.show();

        this.createVisuals();
	}

	private void createVisuals(){
        tiles.removeAll(tiles);
        shuffledWordArray.removeAll(shuffledWordArray);
       
	    String shuffledWord = this.wordBank.getShuffled();
		GameStage.MAP_NUM_COLS = shuffledWord.length();
		
        for (int i = 0; i < shuffledWord.length(); i++) {
        	 shuffledWordArray.add(shuffledWord.charAt(i));
        }

			for(int j=0;j<GameStage.MAP_NUM_COLS;j++){
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

	static void setGameOver(int score){
		PauseTransition transition = new PauseTransition(Duration.seconds(1));
		transition.play();

		transition.setOnFinished(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				GameOverStage gameover = new GameOverStage();
				gameover.setGameover(score);
				stage.setScene(gameover.getScene());
			}
		});
	}

	private void removeChildren() {
		mainPane.getChildren().clear();
	}

	private void setMainPaneContents(){

		 //loop that will set the constraints of the elements in the grid pane
	     int counter=0;
	     for(int row=0;row<GameStage.MAP_NUM_ROWS;row++){
	    	 for(int col=0;col<GameStage.MAP_NUM_COLS;col++){
	    		 // map each land's constraints
	    		 GridPane.setConstraints(tiles.get(counter).getImageView(),col,row);
	    		 counter++;
	    	 }
	     }

	   //loop to add each land element to the gridpane/map
	     for(Element tile: tiles){
	    	 this.mainPane.getChildren().add(tile.getImageView());
	     }
	}

	//method to set size and location of the grid pane
	private void setChatPanePropoerties(){
		//set the map to x and y location; add border color to see the size of the gridpane/map
		this.chatPane.setLayoutX(GameStage.WINDOW_WIDTH*0.66);
	    this.chatPane.setLayoutY(GameStage.WINDOW_HEIGHT*0.03);
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

                if (wordBank.checkAnswer(stringInput)) {
                	long score = this.user.givePoints(wordBank.getAnswer().length());
                	this.removeChildren();
//                	this.textScore.setText("SCORE: " + this.user.getPoints());
                	this.displayAllMessages.appendText(User.getName() + ": " +stringInput+ " ++" + score +"Point(s)\n" ); // CORRECT
                    this.wordBank.randomizeWord();
            		this.createVisuals();
                } else {
                	// this.user.setLives();
                	this.displayAllMessages.appendText(User.getName() + ": " +stringInput + "\n");
                }
                System.out.println("user points: "+ User.getPoints());
                System.out.println("answer: " + this.wordBank.getAnswer());
            }
        });
	}

	private void setMainPaneProperties(){
	    this.mainPane.setHgap(5);

	    switch (MAP_NUM_COLS) {
	    	case 4:
	    		this.mainPane.setLayoutX(GameStage.WINDOW_WIDTH*0.2);
	            break;
	    	case 5:
	    		this.mainPane.setLayoutX(GameStage.WINDOW_WIDTH*0.16);
	            break;
	    	case 6:
	    		this.mainPane.setLayoutX(GameStage.WINDOW_WIDTH*0.12);
	            break;
	    	case 7:
	    		this.mainPane.setLayoutX(GameStage.WINDOW_WIDTH*0.08);
	            break;
	    	case 8:
	    		this.mainPane.setLayoutX(GameStage.WINDOW_WIDTH*0.05);
	            break;
	    	case 9:
	    		this.mainPane.setLayoutX(GameStage.WINDOW_WIDTH*0.02);
	            break;
	    }
	    this.mainPane.setLayoutY(GameStage.WINDOW_HEIGHT*0.55);
	}
	
	Stage getStage() {
		return stage;
	}
	
	public Scene getScene() {
		return this.scene;
	}


}

