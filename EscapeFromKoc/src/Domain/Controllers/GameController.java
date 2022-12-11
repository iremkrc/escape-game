package Domain.Controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import Domain.Alien.Alien;
import Domain.Game.Building;
import Domain.GameObjects.GameObject;
import UI.KeyFoundAlert;
import UI.StartFrame;

public class GameController{

    private PlayerController player;
	private AlienController alienController;
    private static GameController instance;
	private PowerupController powerupController;
	boolean isPaused = false;
	boolean isOver = false;
	private boolean buildingModeDone = false;
	public int timeLeft;
	public int currentBuildingIndex = 0;
	public Building currentBuilding;
	public final int buildingCount = 6;
	private String[] buildingNames = {"Student Center","CASE","SOS","SCI","ENG","SNA"}; 

	private int[] objCounts = {5,7,10,14,19,25};
	//private int[] objCounts = {1,1,1,1,1,1};

	private LinkedList<Building> buildings = new LinkedList<Building>();
	private LinkedList<GameObject> gameObjectList = new LinkedList<GameObject>();
	
	
	public GameController() {
		for(int i=0;i<buildingCount;i++) {
			Building building = new Building(buildingNames[i],objCounts[i]);
			buildings.add(building);
		}
		currentBuilding = buildings.get(currentBuildingIndex);
	}
	
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

	public void setPowerupController(PowerupController powerupController) {
		this.powerupController = powerupController;
	}

	public PowerupController getPowerupController() {
		return powerupController;
	}

	public void setAlienController(AlienController alienController) {
		this.alienController = alienController;
	}

	public AlienController getAlienController() {
		return alienController;
	}

    public boolean isOver() {
		return isOver;
	}
    
    public boolean isBuildingModeDone() {
		return buildingModeDone;
	}
    
    public void setBuildingModeDone(boolean b) {
		buildingModeDone = b;
	}

    public int getTimeLeft() {
        return timeLeft;
	}

    public void moveAvatar(String direction) {
		player.moveAvatar(direction);
	}

	public void pickKey(int x, int y) {
		for(int i=0; i<currentBuilding.getObjectList().size(); i++) { 
	    	GameObject obj = currentBuilding.getObjectList().get(i);
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
		    			//--------------------------------------------------------------------
		    			// What to do when key is found
		    			KeyFoundAlert alertkey = new KeyFoundAlert();
		    			if(currentBuildingIndex == 5) {
		    				alertkey.alert(currentBuildingIndex);
		    				isOver = true;
		    			}else {
		    				boolean changeBuilding = alertkey.alert(currentBuildingIndex);
			    			if(changeBuilding) {
			    				setCurrentBuilding(currentBuildingIndex + 1);
			    				player.avatar.putAvatarToInitialLocation();
			    			}else {
			    				isOver = true;
			    			}
		    			}
		    			//--------------------------------------------------------------------
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
		this.currentBuilding.gameObjectList.add(object);
	}

	public LinkedList<GameObject> getObjectList() {
		return gameObjectList;
	}
	
	public void setCurrentBuilding(int x) {
		currentBuildingIndex = x;
		currentBuilding = buildings.get(x);
	}
	
	public void addObjectToCurrentBuilding(int x, int y) {
		if(!currentBuilding.getIsFull()) {
			currentBuilding.addObject(x,y);
		}
	}

	public static void main(String[] args) {
		new StartFrame();
	}

	public void initializeRunningMode() {
		for(Building b: buildings) {
			int objCount = b.getIntendedObjectCount();
			int keyObject = ThreadLocalRandom.current().nextInt(0, objCount);
			b.getObjectList().get(keyObject).setContainsKey(true);
		}
	}
}
	