package Domain;

import java.util.Random;

public class Powerup {

	public String type;
	public Location location;
	private int width;
	private int height;
	private double speed;
	public int angle;

	public GameState escapeFromKocGame= GameState.getInstance();
	Random rand = new Random();

	public Powerup(int unitLength) {
		width = unitLength/4;
		height = unitLength/4;
		//location = new Location();
        //speed = 
	}
	
	public int getAngle() {
		return this.angle;
	}

	public void setLocation(double xLocation, double yLocation) {
		location.updateLocation(xLocation, yLocation);
	}

	public String getType() {
		return type;
	}

	public Location getLocation() {
		return location;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
