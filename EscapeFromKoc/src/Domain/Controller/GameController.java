package Domain.Controller;

import Domain.GameObject;
import UI.StartFrame;

public class GameController{

    private PlayerController player;
    private static GameController instance;
	boolean isPaused = false;
	boolean isOver = false;
	public int timeLeft;
	private GameObject obj1;
	private GameObject obj2;

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

	public void pickKey() {
		player.pickKey();
	}

	public void catchPowerup(String type) {
		player.catchPowerup();
	}

	public void incrementScore(double increment) {
		player.incrementScore(increment);
	}
	
	// game controller addition for displaying objects // imported domain.GameObject // hardcoded
	
	public void setObj1(GameObject object) {
		this.obj1 = object;
	}
	
	public void setObj2(GameObject object) {
		this.obj2 = object;
		obj2.setLocation(170, 60);
		obj2.setContainsKey(true);
	}
	
	public GameObject getObj1() {
		return obj1;
	}

	public GameObject getObj2() {
		return obj2;
	}
	
	//

	public static void main(String[] args) {

		new StartFrame();
	}



















}



