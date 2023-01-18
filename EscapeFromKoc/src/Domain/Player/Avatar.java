package Domain.Player;

import Domain.GameObjects.Key;
import Domain.Controllers.GameController;
import Domain.Game.Location;

public class Avatar {

	public GameController game;
    public int width;
	public int height;
	int angle;
	double speed;
	Location location;
	
    public Avatar(int unitLength) {
    	game = GameController.getInstance();
		width = unitLength;
		height = unitLength;
		location = new Location(game.getGridSize(), game.getGridSize());
		speed = game.getGameState().gridSize;
	}
    
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


	public Location getLocation() {
		return location;
	}

	public void setLocation(double xLocation, double yLocation) {
		this.location = new Location(xLocation,yLocation);
	}
	
	public void setLocation(Location loc) {
		this.location = loc;
	}

    public void move(String direction) {
    	// Requires: Game is in the running mode.
        // Modifies: Location of the avatar.
        // Effects: If succeded, changes the Location of the avatar.
    	if(direction.equals("right")) {
			moveRight();
		}
    	else if(direction.equals("left")){
			moveLeft();
		}
		else if(direction.equals("up")){
			moveUp();
		}
		else{
			moveDown();
		}
    }  

	private void moveLeft() {
		if(location.xLocation>game.getGridSize() && game.getGridAvailability(location.getXGrid() - 1, location.getYGrid())) {
			location.updateLocation(location.xLocation-speed, location.yLocation);
		}
	}
	private void moveRight() {
		int maxWidth = game.getGridSize()*game.getGridWidth();
		if(location.xLocation<maxWidth && game.getGridAvailability(location.getXGrid() + 1, location.getYGrid())) {
			location.updateLocation(location.xLocation+speed, location.yLocation);
		}
	}
	private void moveDown() {
		int maxHeight = game.getGridSize()*game.getGridHeight();
		if(location.yLocation<maxHeight && game.getGridAvailability(location.getXGrid(), location.getYGrid() + 1)) {
			location.updateLocation(location.xLocation, location.yLocation+speed);
		}
	}
	private void moveUp() {
		if(location.yLocation>game.getGridSize() && game.getGridAvailability(location.getXGrid(), location.getYGrid() - 1)) {
			location.updateLocation(location.xLocation, location.yLocation-speed);
		}
	}

	public Key pickKey() {
        return null;
    }
    
    public void putAvatarToInitialLocation(){
    	this.setLocation(game.getGridSize(), game.getGridSize());
    }

}
