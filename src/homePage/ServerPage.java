package homePage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import networking.Server;
import soloGame.GameStage;

public class ServerPage {
	// declaration of the attributes
	private Scene scene;
	private Stage stage;
	private GameStage gameStage;

	// customized icon and cursor
	private final Image ICON = new Image ("images/icon.png",500,500,false,false);
	private final Image CURSOR = new Image ("images/cursor.png",500,500,false,false);

	private final Image BG = new Image("images/server.png",GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
	private final Image STARTBTN = new Image("images/start.png",Home.BUTTON_WIDTH, Home.BUTTON_HEIGHT, false, false);
	private ImageView bgView;
	private ImageView startView;
	private Group root;
	private VBox vbox;
	private String host;
	private int port;

	// constructor
	ServerPage (String host, int port){
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT, Color.WHITE);
		this.host = host;
		this.port = port;
		
		this.bgView = new ImageView(BG);
		this.root.getChildren().add(this.bgView);

		this.setProperties();
	
	}

	private void setProperties(){
		this.vbox = new VBox(12);
		vbox.setAlignment(Pos.CENTER);
		//start button
		this.startView = new ImageView(this.STARTBTN);
		this.setHandler(1, startView, this.gameStage);

		vbox.getChildren().addAll(startView);
		vbox.setLayoutX(GameStage.WINDOW_WIDTH*.38); //set the x coordinate
		vbox.setLayoutY(370); // set the y coordinate
		this.root.getChildren().add(vbox); //add the vbox to the root
	}
	
	//this method will set the event handler for the buttons
	private void setHandler(int button, ImageView imgview, GameStage gameStage){
		imgview.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(button == 1){ //start server
					  Server server = new Server(host, port);
				}
			}
		});
	}

	void setStage(Stage stage) {
		this.stage = stage;

		this.scene.setCursor(new ImageCursor(CURSOR));
		stage.getIcons().add(ICON);

		this.stage.setTitle("Super Shufflr");
		this.stage.setScene(this.scene);
		this.stage.show();
		
	}
	
	// getter
	public Scene getScene(){
		return this.scene;
	}

}
