package Domain.Controller;

import UI.StartFrame;

public class GameController{

    private PlayerController player;
    private static GameController instance;
	boolean isPaused = false;
	boolean isOver = false;
	public int timeLeft;

    public static GameController getInstance() {
		if (instance == null)
			instance = new GameController();
		return instance;
	}

    public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

    public boolean isPaused() {
        return isPaused;
    }

    public void isGameOver() {
		boolean isDead = player.getHealth() <= 0;
		boolean noTime = timeLeft <= 0;
		isOver = isDead||noTime;
		if(isOver) {
			double collectionTime = (600000-timeLeft) / 1000;
			player.incrementScore(1 / collectionTime);
		}
	}
   
    public void setPlayer(PlayerController player) {
		this.player=player;
	}

    public PlayerController getPlayer() {
		return player;
	}

    public boolean isOver() {
		return isOver;
	}

    public int getTimeLeft() {
        return timeLeft;
	}

    public void moveAvatar(String direction) {
		player.moveAvatar(direction);
	}

	public void pickKey(int x, int y) {
		player.pickKey();
	}

	public void catchPowerup(String type) {
		player.catchPowerup();
	}

	public void incrementScore(double increment) {
		player.incrementScore(increment);
	}

	public static void main(String[] args) {

		new StartFrame();
	}



















}



