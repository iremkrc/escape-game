package Domain.Controllers;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import Domain.Game.Building;
import Domain.Game.PlayerState;
import Domain.Game.GameState;
import Domain.GameObjects.GameObject;
import UI.KeyFoundAlert;
import UI.StartFrame;

public class GameController{

    private PlayerController player;
	private PlayerState playerState;
	private AlienController alienController;
	private GameState gameState;
	private PowerupController powerupController;
    private static GameController instance;
	private boolean buildingModeDone = false;
	public int timeLeft;
	public Building currentBuilding;
	private LinkedList<Building> buildings = new LinkedList<Building>();
	private LinkedList<GameObject> gameObjectList = new LinkedList<GameObject>();
	
	public GameController() {
		gameState = new GameState();
		for(int i=0 ;i<gameState.getBuildingCount() ;i++) {
			Building building = new Building(gameState.buildingNames[i],gameState.objCounts[i]);
			buildings.add(building);
		}
		currentBuilding = buildings.get(gameState.getCurrentBuildingIndex());
	}
	
    public static GameController getInstance() {
		if (instance == null)
			instance = new GameController();
		return instance;
	}

    public void setPaused(boolean isPaused) {
    	gameState.setPaused(isPaused);
	}

    public boolean isPaused() {
        return gameState.isPaused();
    }

    public void isGameOver() {
		boolean isDead = playerState.getHealth() <= 0;
		boolean noTime = timeLeft <= 0;
		gameState.setIsOver(isDead||noTime);
		if(gameState.isOver()) {
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

	public void setAlienController(AlienController alienController) {
		this.alienController = alienController;
	}

	public AlienController getAlienController() {
		return alienController;
	}

	public void setPowerupController(PowerupController powerupController) {
		this.powerupController = powerupController;
	}

	public PowerupController getPowerupController() {
		return powerupController;
	}

    public boolean isOver() {
		return gameState.isOver();
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
		    			//--------------------------------------------------------------------
		    			// What to do when key is found
		    			KeyFoundAlert alertkey = new KeyFoundAlert();
		    			if(gameState.getCurrentBuildingIndex() == 5) {
		    				alertkey.alert(gameState.getCurrentBuildingIndex());
		    				gameState.setIsOver(true);
		    			}else {
		    				boolean changeBuilding = alertkey.alert(gameState.getCurrentBuildingIndex());
			    			if(changeBuilding) {
			    				setCurrentBuilding(gameState.getCurrentBuildingIndex() + 1);
			    				player.avatar.putAvatarToInitialLocation();
			    			}else {
			    				gameState.setIsOver(true);
			    			}
		    			}
		    			//--------------------------------------------------------------------
		    		}
		    	}
	    	}
		}
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
		gameState.setCurrentBuildingIndex(x);
		currentBuilding = buildings.get(x);
	}
	
	public int getCurrentBuildingIndex() {
		return gameState.getCurrentBuildingIndex();
	}
	
	public void addObjectToCurrentBuilding(int x, int y) {
		if(!currentBuilding.getIsFull()) {
			currentBuilding.addObject(x,y);
		}
	}

	public int getBuildingCount() {
		return gameState.getBuildingCount();
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
	