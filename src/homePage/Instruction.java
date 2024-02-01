package homePage;

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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import soloGame.GameStage;

public class Instruction {
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
	private final Image BG = new Image("images/howPage.png",GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
	private ImageView backbtnView;
	private ImageView bgView;
	private Group root;
	private VBox vbox;
	private GameStage gameStage;
	// constructor
	Instruction (){
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT, Color.WHITE);
		
		this.bgView = new ImageView(BG);
		this.root.getChildren().add(this.bgView);

		this.setProperties();
	}

	private void setProperties(){
		this.vbox = new VBox(12);
		vbox.setAlignment(Pos.CENTER);

		//backplayer button
		this.backbtnView = new ImageView(this.BACKBTN);
		this.setHandler(1, backbtnView, this.gameStage);

		vbox.getChildren().addAll(backbtnView);
		vbox.setLayoutX(GameStage.WINDOW_WIDTH*.38); //set the x coordinate
		vbox.setLayoutY(450); // set the y coordinate

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

	//this method will set the event handler for the buttons
	private void setHandler(int button, ImageView imgview, GameStage gameStage){
		imgview.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(button == 1){ //new game
					System.out.println(">>> BACK");
					Home home = new Home();
					home.setStage(stage);
					stage.setScene(home.getScene());
				}
			}
		});
	}

	// getter
	public Scene getScene(){
		return this.scene;
	}

}
