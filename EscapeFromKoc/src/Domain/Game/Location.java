package Domain.Game;

import Domain.Controllers.GameController;

public class Location {
    public double xLocation;
	public double yLocation;
	public int xGrid;
	public int yGrid;
	
	
	public Location(double xLocation, double yLocation) {
		this.xLocation=xLocation;
		this.yLocation=yLocation;
		this.xGrid = (int) (xLocation/GameState.gridSize);
		this.yGrid = (int) (yLocation/GameState.gridSize);
	}
	
	public void updateLocation(double newXLocation, double newYLocation) {
		xLocation=newXLocation;
		yLocation=newYLocation;
		this.xGrid = (int) (newXLocation/GameState.gridSize);
		this.yGrid = (int) (newYLocation/GameState.gridSize);
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