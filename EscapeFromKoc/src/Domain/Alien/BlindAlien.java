package Domain.Alien;

import Domain.Controllers.GameController;
import java.util.concurrent.ThreadLocalRandom;

import Domain.Game.Location;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BlindAlien implements Alien {

    public int width;
	public int height;
	public int size;
	public String type;
	private Location location;
	private boolean empty;
    private Location avatarLocation;
	private boolean isBottlePowerupActive;
    private double speed;
    private GameController game;

    public BlindAlien() {
        type = "Blind";
        empty = false;
        game = GameController.getInstance();
        width = game.getGridWidth();
        height = game.getGridHeight();
        size = game.getGridSize();
        speed = game.getGridSize();
        location = game.getAvailableLocation();
        this.avatarLocation = game.getPlayer().getAvatar().getLocation(); 
        this.isBottlePowerupActive = game.getPlayer().getPlayerState().getIsBottlePowerupActive();
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void action() {
        double xDistance = Math.abs(avatarLocation.getXLocation() - location.getXLocation());
    	double yDistance = Math.abs(avatarLocation.getYLocation() - location.getYLocation());
        
        //System.out.println(game.getGameState().getIsBottlePowerupActive());
        if(!game.getGameState().getIsBottlePowerupActive()) {
            //alien randomly moves 
    		moveRandomly();
            if(xDistance + yDistance <= game.getGridSize()){
            	game.getPlayer().getPlayerState().setHealth(0);
            }
    	}if(game.getGameState().getIsBottlePowerupActive() && !game.getGameState().isBottleDirectionSettable()){
            //alien goes in the direction of plastic bottle powerup
            System.out.println(game.getBottlePowerupDirection());
            if(!moveToDirection(game.getBottlePowerupDirection())) {
            	game.getGameState().setIsBottlePowerupActive(false);
            }
            if(xDistance + yDistance <= game.getGridSize()){
            	game.getPlayer().getPlayerState().setHealth(0);
            }    
        }       
    }

    @Override
	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}  

    public boolean moveLeft() {
    	if(location.xLocation>game.getGridSize() && game.getGridAvailability(location.xGrid-1, location.yGrid)) {
			location.updateLocation(location.xLocation-speed, location.yLocation);
			return true;
		}
		return false;
	}
    
	public boolean moveRight() {
		int maxWidth = game.getGridSize()*game.getGridWidth();
		if(location.xLocation<maxWidth-width && game.getGridAvailability(location.xGrid+1, location.yGrid)) {
			location.updateLocation(location.xLocation+speed, location.yLocation);
			return true;
		}
		return false;
	}
	
	public boolean moveDown() {
		int maxHeight = game.getGridSize()*game.getGridHeight();
		if(location.yLocation<maxHeight-width && game.getGridAvailability(location.xGrid, location.yGrid+1)) {
			location.updateLocation(location.xLocation, location.yLocation+speed);
			return true;
		}
		return false;
	}
	
	public boolean moveUp() {
		if(location.yLocation>game.getGridSize() && game.getGridAvailability(location.xGrid, location.yGrid-1)) {
			location.updateLocation(location.xLocation, location.yLocation-speed);
			return true;
		}
		return false;
	}

	public void moveRandomly(){
    	List<String> directionsList = Arrays.asList("South", "North", "West", "East");
    	while(true) {
    		int randomTypeIndex = ThreadLocalRandom.current().nextInt(directionsList.size());
            String direction = directionsList.get(randomTypeIndex);
            if(moveToDirection(direction)) {
            	break;
            }
    	} 
    }

    public boolean moveToDirection(String direction){
        if(direction.equals("East")) {
			return moveRight();
		}
    	else if(direction.equals("West")){
			return moveLeft();
		}
		else if(direction.equals("North")){
			return moveUp();
		}
		else if(direction.equals("South")){
			return moveDown();
		}
        return false;
    }
    
    public Location getLocation() {
		return location;
	}
    
    public int getSize() {
    	return size;
    }
    
	@Override
	public void setLocation(Location loc) {
		this.location = loc;
	}
}
