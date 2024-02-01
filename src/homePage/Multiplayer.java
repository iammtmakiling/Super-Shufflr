package homePage;

import networking.MPGameStage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import soloGame.GameStage;

public class Multiplayer {
	// declaration of the attributes
	private Canvas canvas;
	private static GraphicsContext gc;
	private Scene scene;
	private Stage stage;
	private StackPane pane;

	private static int pageCounter = 1;

	// customized icon and cursor
	private final Image ICON = new Image ("images/icon.png",500,500,false,false);
	private final Image CURSOR = new Image ("images/cursor.png",500,500,false,false);

	private final Image BACKBTN = new Image("images/back.png",Home.BUTTON_WIDTH, Home.BUTTON_HEIGHT, false, false);
	private final Image CREATE_BTN = new Image("images/createRoom.png",Home.BUTTON_WIDTH, Home.BUTTON_HEIGHT, false, false);
	private final Image JOIN_BTN = new Image("images/joinRoom.png",Home.BUTTON_WIDTH, Home.BUTTON_HEIGHT, false, false);
	private final Image BG = new Image("images/multiplayerPage.png",GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
	private ImageView backbtnView;
	private ImageView bgView;
	private ImageView create_btnView;
	private ImageView join_btnView;
	private Group root;
	private VBox vbox;
	private GameStage gameStage;
	private String userName;
	private String host;
	private int port;

	// constructor
	Multiplayer (String userName){
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT, Color.WHITE);
		this.userName = userName;
		this.bgView = new ImageView(BG);
		this.root.getChildren().add(this.bgView);

	    // Set the focus to the root node
	    root.requestFocus();
	   
		this.setProperties();
	}

	private void setProperties(){
		this.vbox = new VBox(12);
		vbox.setAlignment(Pos.CENTER);

		// TextField
		TextField portField = new TextField();
		TextField hostField = new TextField();
		
		//Change Height and Width
		portField.setPrefWidth(200);
		portField.setPrefHeight(30);
		portField.setMaxWidth(200);
		portField.setMaxHeight(30);
		hostField.setPrefWidth(200);
		hostField.setPrefHeight(30);
		hostField.setMaxWidth(200);
		hostField.setMaxHeight(30);
		portField.setStyle("-fx-focus-color: brown;");
		hostField.setStyle("-fx-focus-color: brown;");

        // Set the placeholder (prompt text)
        hostField.setPromptText("Host No.");
        portField.setPromptText("Port No.");
        
        // Add an event handler to capture text changes in the input field
        portField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                port = Integer.parseInt(newValue);
                // Perform actions with the integer value
                System.out.println("Input is an integer: " + port);
            } catch (NumberFormatException e) {
                System.out.println("Input is not a valid integer");
            }
        });

        hostField.textProperty().addListener((observable, oldValue, newHost) -> {
            host = newHost; // Store the updated text in the userInput variable
            System.out.println("Host: " + host);
        });
        
		//create button
		this.create_btnView = new ImageView(this.CREATE_BTN);
		this.setHandler(1, create_btnView, this.gameStage);

		//join button
		this.join_btnView = new ImageView(this.JOIN_BTN);
		this.setHandler(2, join_btnView, this.gameStage);

		//back button
		this.backbtnView = new ImageView(this.BACKBTN);
		this.setHandler(3, backbtnView, this.gameStage);

		vbox.getChildren().addAll(portField, hostField, create_btnView, join_btnView, backbtnView);
		vbox.setLayoutX(GameStage.WINDOW_WIDTH*.38); //set the x coordinate
		vbox.setLayoutY(200); // set the y coordinate

		this.root.getChildren().add(vbox); //add the vbox to the root
	}

	void setStage(Stage stage) {
		this.stage = stage;

		this.scene.setCursor(new ImageCursor(CURSOR));
		stage.getIcons().add(ICON);

		this.stage.setTitle("Super Shufflr");
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	// back button
	//this method will set the event handler for the buttons
	private void setHandler(int button, ImageView imgview, GameStage gameStage){
		imgview.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(button == 3){ //back
					System.out.println(">>> BACK");
					Home home = new Home();
					home.setStage(stage);
					stage.setScene(home.getScene());
				} else if (button == 1){
					ServerPage serverPage = new ServerPage(host, port);
					serverPage.setStage(stage);
					stage.setScene(serverPage.getScene());
				} else if (button == 2){
					MPGameStage gameStage = new MPGameStage(userName, port ,host);
					gameStage.setStage(stage);
					stage.setScene(gameStage.getScene());
				}
			}
		});
	}

	// getter
	public Scene getScene(){
		return this.scene;
	}

}
