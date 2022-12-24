package Domain.Controllers;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import Domain.Game.Building;
import Domain.Game.PlayerState;
import Domain.Game.GameState;
import Domain.GameObjects.GameObject;
import Domain.GameObjects.Powerups.IPowerup;
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
	// remaining time in seconds
	private int timeLeft;
	// total time in minutes
	private int totalTime;
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
		gameState.setNewBuildingTime();
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
	
	public boolean isKeyFound() {
		return gameState.isKeyFound();
	}
	public void setKeyFound(boolean b) {
		gameState.setKeyFound(b); 
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

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

    public void moveAvatar(String direction) {
		player.moveAvatar(direction);
	}

	public void catchPowerUp(int x, int y) throws Exception {
		IPowerup powerup = powerupController.getPowerup();
        if(powerup != null){
            double pwupX = powerup.getLocation().getXLocation();
		    double pwupY = powerup.getLocation().getYLocation();
		    int pwupH = powerup.getHeight();
		    int pwupW = powerup.getWidth();
		    if(x>=pwupX && x<=pwupX+pwupW && y>=pwupY && y<=pwupY+pwupH) {
				System.out.println("catch powerup");
				powerupController.deletePowerup();
				switch (powerup.getType()) {
					case "time":
						gameState.setTime(gameState.getTime() + 5);
						break;
					case "hint":
						player.getPlayerState().getInventory().incrementPowerups(powerup.getType());
						break;
					case "vest":
						player.getPlayerState().getInventory().incrementPowerups(powerup.getType());
						break;
					case "bottle":
						player.getPlayerState().getInventory().incrementPowerups(powerup.getType());
						break;
					case "life":
						player.getPlayerState().setHealth(player.getPlayerState().getHealth()+1);
						break;
					default:
						throw new IllegalArgumentException("Unknown type "+ powerup.getType());
				}
			}
        }
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
		    			performKeyFoundAction();
		    		}
		    	}
	    	}
		}
	}

	public void performKeyFoundAction(){
		System.out.println("Key is found");
		// What to do when key is found
		KeyFoundAlert alertkey = new KeyFoundAlert();
		this.setPaused(true);
		setKeyFound(true);
		if(gameState.getCurrentBuildingIndex() == 5) {
			alertkey.alert(gameState.getCurrentBuildingIndex());
			gameState.setIsOver(true);
		}else {
			boolean changeBuilding = alertkey.alert(gameState.getCurrentBuildingIndex());
			if(changeBuilding) {
				setCurrentBuilding(gameState.getCurrentBuildingIndex() + 1);
				player.avatar.putAvatarToInitialLocation();
				this.getPowerupController().setPowerup(null);
				this.getAlienController().setAlien(null);
				setNewBuildingTime();
				this.setPaused(false);
			}else {
				gameState.setIsOver(true);
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

	public void setNewBuildingTime() {
		gameState.setTime(20*gameState.objCounts[getCurrentBuildingIndex()]);
	}
	
	public void addObjectToCurrentBuilding(int x, int y) {
		if(!currentBuilding.getIsFull()) {
			currentBuilding.addObject(x,y);
		}
	}

	public int getBuildingCount() {
		return gameState.getBuildingCount();
	}
	
	public GameState getGameState() {
		return gameState;
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
	