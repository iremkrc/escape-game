package Domain.Controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import Domain.Game.Building;
import Domain.Game.PlayerState;
import Domain.Game.GameState;
import Domain.Game.Location;
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
	public Building currentBuilding;
	private LinkedList<Building> buildings = new LinkedList<Building>();
	private LinkedList<GameObject> gameObjectList = new LinkedList<GameObject>();
	private Map<String, Integer> buildingKeyMap = new HashMap<>();
	private Location keyLocation;
	private String bottlePowerupDirection;
	
	public GameController() {
		gameState = new GameState();
		
		for(int i=0 ;i<gameState.getBuildingCount() ;i++) {
			Building building = new Building(gameState.buildingNames[i],gameState.objCounts[i], gameState.width, gameState.height, gameState.gridSize);
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
/* 
    public void isGameOver() {
		boolean isDead = playerState.getHealth() <= 0;
		boolean noTime = timeLeft <= 0;
		gameState.setIsOver(isDead||noTime);
		if(gameState.isOver()) {
			double collectionTime = (600000-timeLeft) / 1000;
			player.incrementScore(1 / collectionTime);
		}
	}
*/
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

	public Map<String, Integer> getBuildingKeyMap() {
		return buildingKeyMap;
	}

    public void moveAvatar(String direction) {
		player.moveAvatar(direction);
		
		if(this.isKeyFound()) {
			double doorX = this.currentBuilding.getDoor().getLocation().getXLocation();
	    	double doorY = this.currentBuilding.getDoor().getLocation().getYLocation();
    		double avtX = player.getAvatar().getLocation().xLocation;
    		double avtY = player.getAvatar().getLocation().yLocation;
    		if(Math.abs(avtY-doorY)<=gameState.gridSize && Math.abs(avtX-doorX)<=gameState.gridSize) {
    			performDoorPassingAction();
    		}
		}
	}

	public void catchPowerUp(int x, int y) throws Exception {
		// Requires: Game passed to running mode
		// Modifies: Powerup is found or not and if found catches the powerup
		// Effects: if powerup is found, increments time, life or powerup count in inventory according to powerup type
		IPowerup powerup = powerupController.getPowerup();
        if(powerup != null){
            double pwupX = powerup.getLocation().getXLocation();
		    double pwupY = powerup.getLocation().getYLocation();
		    int pwupH = powerup.getSize();
		    int pwupW = powerup.getSize();
		    if(x>=pwupX && x<=pwupX+pwupW && y>=pwupY && y<=pwupY+pwupH) {
				System.out.println("catch powerup");
				powerupController.deletePowerup(powerup);
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

	public void activatePowerUp(String type) throws Exception{
		if(type == "hint"){
			this.player.useHintPowerUp();
		}
		else if(type == "bottle"){
			this.player.useBottlePowerUp();
		}
	}

	public String getBottlePowerupDirection() {
		return bottlePowerupDirection;
	}
    
    public void setBottlePowerupDirection(String bottlePowerupDirection) {
		this.bottlePowerupDirection = bottlePowerupDirection;
	}

	public Location getHintLocation(){
		double x = gameState.gridSize; 
		double y = gameState.gridSize;
		for(GameObject o: this.currentBuilding.getObjectList()){
			if(o.isContainsKey()) {
				x = o.getLocation().getXLocation()-2*gameState.gridSize;
				y = o.getLocation().getYLocation()-2*gameState.gridSize;
				break;
			}
		}
		if(x<gameState.gridSize) x=gameState.gridSize;
		if(y<gameState.gridSize) y=gameState.gridSize;
		if(x>(this.getGridWidth()-3)*gameState.gridSize) x=(this.getGridWidth()-3)*gameState.gridSize;
		if(y>(this.getGridHeight()-3)*gameState.gridSize) y=(this.getGridHeight()-3)*gameState.gridSize;
		return new Location(x,y);
	}

	public void pickKey(int x, int y) {
		// Requires: Game passed to running mode
		// Modifies: Key is found or not and if found changes building
		// Effects: if key is found changes building.
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
		    		if(Math.abs(avtY-objY)<=gameState.gridSize && Math.abs(avtX-objX)<=gameState.gridSize) {
		    			performKeyFoundAction();
		    		}
		    	}
	    	}
		}
	}

	public void performKeyFoundAction(){
		System.out.println("Key is found");
		// What to do when key is found
		setKeyFound(true);
		this.currentBuilding.setDoor(true);
	}
	
	public void performDoorPassingAction() {
		if(gameState.getCurrentBuildingIndex() == 5) {
			gameState.setIsOver(true);
		}else {
			this.getPowerupController().setPowerup(null);
			this.getAlienController().setAlien(null);
			this.getAlienController().resetAlienTime();
			setCurrentBuilding(gameState.getCurrentBuildingIndex() + 1);
			player.avatar.putAvatarToInitialLocation();
			setNewBuildingTime();
			this.setPaused(false);
			this.getGameState().setHintActive(false);
		}
	}
	
	public boolean getGridAvailability(int x, int y) {
		return currentBuilding.getGridAvailability(x, y);
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

	public Building getCurrentBuilding() {
		return currentBuilding;
	}

	public void setNewBuildingTime() {
		gameState.setTime(20*gameState.objCounts[getCurrentBuildingIndex()]);
	}
	
	public void addObjectToCurrentBuilding(int x, int y) {
			currentBuilding.addObject(x,y);
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
			keyLocation = b.getObjectList().get(keyObject).getLocation();
			buildingKeyMap.put(b.getBuildingName(), keyObject);
		}
	}
	
	public Location getAvailableLocation() {
		/*
		 * This function returns a location that is available.
		 * It checks the location of objects, avatar, power-ups and aliens
		 * Then returns a location that does not include any of these.
		 * This function copes with the collision problem while
		 * creating an alien or power-up.
		 */
		int x = 0;
        int y = 0;
		int avtX = player.getAvatar().getLocation().xGrid;
		int avtY = player.getAvatar().getLocation().yGrid;
		while(true) {
			x = ThreadLocalRandom.current().nextInt(gameState.width-1)+1;
			y = ThreadLocalRandom.current().nextInt(gameState.height-1)+1;
			if(!getGridAvailability(x,y)) {
				continue;
			}
			if(avtX == x && avtY == y) {
				continue;
			}
			if(this.alienController.getAlien() != null) {
				int alienX = alienController.getAlien().getLocation().xGrid;
				int alienY = alienController.getAlien().getLocation().yGrid;
				if(alienX == x && alienY == y) {
					continue;
				}
			}
			if(this.powerupController.getPowerup() != null) {
				int powX = powerupController.getPowerup().getLocation().xGrid;
				int powY = powerupController.getPowerup().getLocation().yGrid;
				if(powX == x && powY == y) {
					continue;
				}
			}
			break;
		}
        return new Location(x*gameState.gridSize, y*gameState.gridSize);		
	}

	public void setBuildings(LinkedList<Building> buildings) {
		this.buildings = buildings;
		
	}
	
	public int getGridSize() {
		return gameState.gridSize;
	}
	
	public int getGridWidth() {
		return gameState.width;
	}
	
	public int getGridHeight() {
		return gameState.height;
	}
	
	public int getPlayerHealth() {
		return player.getPlayerState().getHealth();
	}
}
	