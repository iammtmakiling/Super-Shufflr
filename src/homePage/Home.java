package homePage;

// when we run the program in Main, this class will be instantiated and run

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.ImageCursor;
import java.io.File;

import game.User;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import soloGame.GameStage;

public class Home {

// declaration of attributes
//private StackPane pane;
private Scene scene;
private Stage stage;

private Group root;
//private Canvas canvas;
//private static GraphicsContext gc;

protected final static int BUTTON_WIDTH = 225;
protected final static int BUTTON_HEIGHT = 50;


// necessary images and background music throughout the game
private final Image BG = new Image("images/main.gif",GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
private final Image solo = new Image("images/solo.png", BUTTON_WIDTH, BUTTON_HEIGHT, false, false);
private final Image instruction = new Image("images/instruction.png", BUTTON_WIDTH, BUTTON_HEIGHT, false, false);
private final Image about = new Image("images/about.png", BUTTON_WIDTH, BUTTON_HEIGHT, false, false);
private final Image multiplayer = new Image("images/multiplayer.png",BUTTON_WIDTH, BUTTON_HEIGHT, false, false);
private final Image title = new Image("images/title.png",500, 250, false, false);

private ImageView soloView;
private ImageView multiplayerView;
private ImageView aboutView;
private ImageView instructionView;
private ImageView bgView;
private ImageView titleView;
private VBox vbox;

private final Image ICON = new Image ("images/icon.png",500,500,false,false);
private final Image CURSOR = new Image ("images/cursor.png",500,500,false,false);
//public static MediaPlayer mediaPlayer;
private GameStage gameStage;

private String userName = "";
// constructor
public Home(){
//	this.music();
	
	this.root = new Group();
	this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT, Color.WHITE);
	this.bgView = new ImageView(BG);
	this.root.getChildren().add(this.bgView);

    // Set the focus to the root node
    root.requestFocus();
    
	this.setProperties();
 }

	// for the background music
//	private void music(){
//		Media sound = new Media(getClass().getResource("/sfx/bgmusic.mp3").toExternalForm());
//		mediaPlayer = new MediaPlayer(sound);
//		mediaPlayer.play(); // plays the music
//		mediaPlayer.setVolume(0.05);
//	}

	private void setProperties(){
		this.vbox = new VBox(12);
		vbox.setAlignment(Pos.CENTER);

		//soloplayer button
		this.titleView = new ImageView(this.title);
		titleView.setPreserveRatio(true);

		// TextField
		TextField inputField = new TextField();
		inputField.setPrefWidth(200);
		inputField.setPrefHeight(30);
		inputField.setMaxWidth(200);
		inputField.setMaxHeight(30);

        // Set the placeholder (prompt text)
        inputField.setPromptText("Player Name");

        // Add an event handler to capture text changes in the input field
        inputField.textProperty().addListener((observable, oldValue, newValue) -> {
            userName = newValue; // Store the updated text in the userInput variable
        });

		//soloplayer button
		this.soloView = new ImageView(this.solo);
		this.setHandler(1,soloView, this.gameStage);

		//multiplayer button
		this.multiplayerView = new ImageView(this.multiplayer);
		this.setHandler(2, multiplayerView,this.gameStage);

		//instructions button
		this.instructionView = new ImageView(this.instruction);
		this.setHandler(3, instructionView, this.gameStage);

		//about page button
		this.aboutView = new ImageView(this.about);
		this.setHandler(4, aboutView,this.gameStage);

		//add the png buttons to the vbox
		vbox.getChildren().addAll(titleView, inputField, soloView,  multiplayerView, instructionView, aboutView);
		vbox.setLayoutX(GameStage.WINDOW_WIDTH*.25); //set the x coordinate
		vbox.setLayoutY(40); // set the y coordinate

		this.root.getChildren().add(vbox); //add the vbox to the root
	}

	 public void setStage(Stage stage) {
		this.stage = stage;

		this.scene.setCursor(new ImageCursor(CURSOR));
		stage.getIcons().add(ICON);

		this.stage.setTitle("Super Shufflr");
		this.stage.setScene(this.scene);
		this.stage.show();
	 }

	//this method will set the event handler for the buttons
		private void setHandler(int button, ImageView imgview, GameStage gameStage){
			imgview.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					if(button == 1 && userName != ""){ //new game
						System.out.println(">>> SOLO");
						GameStage gameStage = new GameStage(userName);
						gameStage.setStage(stage);
						stage.setScene(gameStage.getScene());
					} else if(button == 2  && userName != ""){ // how to play
						User user = new User(userName);
						System.out.println(">>> MULTIPLAYER");
						Multiplayer multiplayerPage = new Multiplayer(userName);
						multiplayerPage.setStage(stage);
						stage.setScene(multiplayerPage.getScene());
					} else if(button == 3){ // about
						System.out.println(">>> INSTRUCTIONS");
						Instruction instructionPage = new Instruction();
						instructionPage.setStage(stage);
						stage.setScene(instructionPage.getScene());
					} else if(button == 4){ //about
						System.out.println(">>> ABOUT");
						About aboutPage = new About();
						aboutPage.setStage(stage);
						stage.setScene(aboutPage.getScene());
					}
				}
			});
		}



	 // getter
	public Scene getScene(){
		return this.scene;
	}
}