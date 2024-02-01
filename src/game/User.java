package game;

import soloGame.GameTimer;

public class User {
	private static String username;
	private String answer;
	private static int points;
	private int lives;


	public User(String name) {
		username = name;
		points = 0;
		this.lives = 3;
	}

	public static String getName(){
		return username;
	}
	protected String getAnswer(){
		return this.answer;
	}
	public static int getPoints(){
		return points;
	}
	protected int getLives(){
		return this.lives;
	}
	public int givePoints(int wordLength){
		int score = (int) Math.round((Double.valueOf(GameTimer.getGameCounter())/30.0)*10.0);
		switch (wordLength) {
			case 7: 
				score=score+3;
				break;
			case 6: 
				score=score+2;
				break;
			case 5: 
				score++;
				break;
			default:
				break;
		}
		System.out.println("Score: "+ score);
		points += score;
		return score;
	}
	public void setLives(){
		this.lives--;
	}
	public void setAnswer(String answer){
		this.answer = answer;
	}
}
