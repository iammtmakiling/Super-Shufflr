package soloGame;

import game.Element;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOverStage {
	private StackPane pane;
	private static Scene scene;
	private static GraphicsContext gc;
	private Canvas canvas;
	private int score;
	public Element element;

	public final Image bg = new Image("images/background1.png",GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
	GameOverStage(){
		this.pane = new StackPane();
		scene = new Scene(pane, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		gc = canvas.getGraphicsContext2D();
	}
	protected void setGameover(int score){
		this.score = score;
		this.setProperties();	//displays

	}
	private void setProperties(){
		GameOverStage.gc.drawImage( this.bg, 0, 0);
		gc.setFont(Font.font("Lato",FontWeight.NORMAL,20));
		gc.setFill(Color.BLACK);
		gc.fillText("Your score is " + this.score, GameStage.WINDOW_WIDTH*0.435, GameStage.WINDOW_HEIGHT*0.645);

		pane.getChildren().add(this.canvas);

		Element button = new Element(Element.EXIT_TYPE, this);
		pane.getChildren().add(button.getImageView());
	}

	public static Scene getScene(){
		return scene;
	}
}
