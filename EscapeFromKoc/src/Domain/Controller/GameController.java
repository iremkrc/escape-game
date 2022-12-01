package Domain.Controller;

import java.util.LinkedList;
import java.util.List;

import Domain.GameObject;
import UI.KeyFoundAlert;
import UI.StartFrame;

public class GameController{

    private PlayerController player;
    private static GameController instance;
	boolean isPaused = false;
	boolean isOver = false;
	public int timeLeft;
	private LinkedList<GameObject> gameObjectList = new LinkedList<GameObject>();
	
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
		for(int i=0; i<gameObjectList.size(); i++) { 
	    	GameObject obj = gameObjectList.get(i);
	    	if(obj.isContainsKey()) {
	    		double objX = obj.getLocation().getXLocation();
		    	double objY = obj.getLocation().getYLocation();
		    	int objH = obj.getHeight();
		    	int objW = obj.getWidth();
		    	if(x>=objX && x<=objX+objW && y>=objY && y<=objY+objH) {
		    		double avtX = player.getAvatar().getLocation().xLocation;
		    		double avtY = player.getAvatar().getLocation().yLocation;
		    		if(Math.abs(avtY-objY)<20 && Math.abs(avtX-objX)<20) {
		    			System.out.println("Key is found");
		    			player.pickKey();
		    			KeyFoundAlert alertkey = new KeyFoundAlert();
		    			alertkey.alert();
		    		}
		    	}
	    	}
		}
	}

	public void catchPowerup(String type) {
		player.catchPowerup();
	}

	public void incrementScore(double increment) {
		player.incrementScore(increment);
	}
	
	
	public void setObject(GameObject object) {
		this.gameObjectList.add(object);
	}

	public LinkedList<GameObject> getObjectList() {
		return gameObjectList;
	}

	public static void main(String[] args) {

		new StartFrame();
	}
}
	