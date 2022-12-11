package Domain.Game;

public class Location {

    public double xLocation;
	public double yLocation;
	public int xGrid;
	public int yGrid;
	
	public Location(double xLocation, double yLocation) {
		this.xLocation=xLocation;
		this.yLocation=yLocation;
		this.xGrid = (int) (xLocation/50);
		this.yGrid = (int) (yLocation/50);
	}
	
	public void updateLocation(double newXLocation, double newYLocation) {
		xLocation=newXLocation;
		yLocation=newYLocation;
		this.xGrid = (int) (newXLocation/50);
		this.yGrid = (int) (newYLocation/50);
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