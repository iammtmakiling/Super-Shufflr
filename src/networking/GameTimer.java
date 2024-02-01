package networking;


import java.util.Dictionary;
import java.util.Enumeration;

import game.User;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/*
 * The GameTimer is a subclass of the AnimationTimer class. It must override the handle method.
 */

public class GameTimer extends AnimationTimer implements Runnable{
	private Scene theScene;

	private static final int GAMETIME = 30; // countdown from 30 seconds 
	private static int GameCounter = 30; // countdown from 30 seconds 
	

	private final Image bg = new Image("images/MPBackground.png",MPGameStage.WINDOW_WIDTH,MPGameStage.WINDOW_HEIGHT,false,false);
	private GraphicsContext gc;

	private MPGameStage MPgamestage;
	private Dictionary<String, Integer> players;
	
	// constructor
	GameTimer(GraphicsContext gc, Scene theScene, MPGameStage mpgamestage){
		this.MPgamestage = mpgamestage;
		this.gc = gc;
		this.theScene = theScene;
	}

	private void statusBar() {
		this.gc.clearRect(0, 0, MPGameStage.WINDOW_WIDTH,MPGameStage.WINDOW_HEIGHT);
		MPGameStage.gc.drawImage(this.bg,0,0);
		// appears above the screen

		//set font type, style, size, and color
		Font theFont = Font.font("Verdana",FontWeight.BOLD,16);
		this.gc.setFont(theFont);
		this.gc.setFill(Color.BLACK);
		
		// POINTS
		
		int counter = 1;
		double x_points = 0;
		double x_name = 0;
		Enumeration<String> k = this.players.keys();
		while (k.hasMoreElements()) {
			String key = k.nextElement();
			switch(counter){
				case 1: x_points = 0.128; x_name = 0.1; break;
				case 2: x_points = 0.2395; x_name = 0.21; break;
				case 3: x_points = 0.356; x_name = 0.325; break;
				case 4: x_points = 0.474; x_name = 0.445; break;
			}
			this.gc.fillText(key, MPGameStage.WINDOW_WIDTH*x_name,MPGameStage.WINDOW_HEIGHT*0.734);
			this.gc.fillText(String.valueOf(this.players.get(key)), MPGameStage.WINDOW_WIDTH*x_points,MPGameStage.WINDOW_HEIGHT*0.786);
			counter ++;
		}
	
		//Time
		this.gc.fillText(String.valueOf(GameTimer.GameCounter), MPGameStage.WINDOW_WIDTH*0.295,MPGameStage.WINDOW_HEIGHT*0.513);

	}

	@Override
	public void handle(long currentNanoTime) {
		System.out.println(GameCounter); 
	}

	@Override
	public void run() {
		while (true) {
			this.players = MPGameStage.getPlayersInfo();
			this.statusBar();
			if (GameTimer.GameCounter <= 0) {
				MPGameStage.setGameOver(this.players);
			}
			try{
				Thread.sleep(1000); // sleeps for 1 second
				GameTimer.setGameCounter(-1); // decrements game counter after 1 second
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	
	private static void setGameCounter (int num){
		GameTimer.GameCounter += num;
	}
	
	public static void resetGameCounter (){
		GameTimer.GameCounter = GAMETIME;
	}
	
	public static int getGameCounter (){
		return GameTimer.GameCounter;
	}

	}