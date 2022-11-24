package Domain.Game;

public class Location {

    public double xLocation;
	public double yLocation;
	
	public Location(double xLocation, double yLocation) {
		this.xLocation=xLocation;
		this.yLocation=yLocation;
	}
	
	public void updateLocation(double newXLocation, double newYLocation) {
		xLocation=newXLocation;
		yLocation=newYLocation;
	}
	
	public double getXLocation() {
		return this.xLocation;
	}

	public double getYLocation() {
		return this.yLocation;
	}
}
