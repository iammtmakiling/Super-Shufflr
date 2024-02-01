package networking;


import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import game.Element;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import networking.GameOverStage;

public class GameOverStage {
	private StackPane pane;
	private static Scene scene;
	private static GraphicsContext gc;
	private Canvas canvas;
	public Element element;
	private  Dictionary<String, Integer> players; 

	public final Image bg = new Image("images/MPGameOver.png",MPGameStage.WINDOW_WIDTH,MPGameStage.WINDOW_HEIGHT,false,false);
	GameOverStage(){
		this.pane = new StackPane();
		scene = new Scene(pane, MPGameStage.WINDOW_WIDTH,MPGameStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(MPGameStage.WINDOW_WIDTH,MPGameStage.WINDOW_HEIGHT);
		gc = canvas.getGraphicsContext2D();
		this.players = new Hashtable<String, Integer>();
	}
	protected void setGameover( Dictionary<String, Integer> players){
		this.players = players;
		this.setProperties();	//displays

	}
	private void setProperties(){
		GameOverStage.gc.drawImage( this.bg, 0, 0);
		gc.setFont(Font.font("Lato",FontWeight.NORMAL,20));
		gc.setFill(Color.BLACK);
				
		int counter = 1;
		double x_points = 0;
		double x_name = 0;
		Enumeration<String> k = this.players.keys();

		while (k.hasMoreElements()) {
			String key = k.nextElement();
			switch(counter){
				case 1: x_points = 0.187; x_name = 0.162; break;
				case 2: x_points = 0.395; x_name = 0.37; break;
				case 3: x_points = 0.605; x_name = 0.58; break;
				case 4: x_points = 0.825; x_name = 0.8; break;
			}
			this.gc.fillText(key, MPGameStage.WINDOW_WIDTH*x_name,MPGameStage.WINDOW_HEIGHT*0.565);
			this.gc.fillText(String.valueOf(this.players.get(key)), MPGameStage.WINDOW_WIDTH*x_points,MPGameStage.WINDOW_HEIGHT*0.675);
			counter ++;
		}
		pane.getChildren().add(this.canvas);
	}

	public static Scene getScene(){
		return scene;
	}
}
