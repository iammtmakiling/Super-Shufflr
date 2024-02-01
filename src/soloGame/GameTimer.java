package soloGame;

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

	private static int GameCounter = 30; // countdown from 30 seconds 
	
	protected GameStage gamestage;

	private final Image bg = new Image("images/SoloBackground.png",GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
	private GraphicsContext gc;

	// constructor
	GameTimer(GraphicsContext gc, Scene theScene, GameStage gamestage){
		this.gamestage = gamestage;
		this.gc = gc;
		this.theScene = theScene;
	}

	private void statusBar() {
		this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		GameStage.gc.drawImage(this.bg,0,0);
		// appears above the screen

		//set font type, style, size, and color
		Font theFont = Font.font("Verdana",FontWeight.BOLD,16);
		this.gc.setFont(theFont);
		this.gc.setFill(Color.BLACK);
		
		//Score Checker
		this.gc.fillText(String.valueOf(User.getPoints()), GameStage.WINDOW_WIDTH*0.297,GameStage.WINDOW_HEIGHT*0.743);
			
		//Time
		this.gc.fillText(String.valueOf(GameTimer.GameCounter), GameStage.WINDOW_WIDTH*0.295,GameStage.WINDOW_HEIGHT*0.513);

	}

	@Override
	public void handle(long currentNanoTime) {
		System.out.println(GameCounter); 
	}

	@Override
	public void run() {
		while (true) {
			this.statusBar();
			if (GameTimer.GameCounter <= 0) {
				GameStage.setGameOver(User.getPoints());
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
		GameTimer.GameCounter = 30;
	}
	
	public static int getGameCounter (){
		return GameTimer.GameCounter;
	}

	}