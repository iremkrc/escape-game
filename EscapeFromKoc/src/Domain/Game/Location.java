package Domain.Game;

import Domain.Controllers.GameController;

public class Location {
	public GameController game;
    public double xLocation;
	public double yLocation;
	public int xGrid;
	public int yGrid;
	
	
	public Location(double xLocation, double yLocation) {
    	game = GameController.getInstance();
		this.xLocation=xLocation;
		this.yLocation=yLocation;
		this.xGrid = (int) (xLocation/game.getGridSize());
		this.yGrid = (int) (yLocation/game.getGridSize());
	}
	
	public void updateLocation(double newXLocation, double newYLocation) {
		xLocation=newXLocation;
		yLocation=newYLocation;
		this.xGrid = (int) (newXLocation/game.getGridSize());
		this.yGrid = (int) (newYLocation/game.getGridSize());
	}
	
	public double getXLocation() {
		return this.xLocation;
	}

	public double getYLocation() {
		return this.yLocation;
	}
	
	public int getXGrid() {
		return this.xGrid;
	}

	public int getYGrid() {
		return this.yGrid;
	}
	
}