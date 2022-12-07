package Domain.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import Domain.GameObjects.Key;
import Domain.GameObjects.Powerups.Powerup;
import Domain.Game.Location;

public class Avatar {

    public int width;
	public int height;
	int angle;
	double speed = 50;
	Location location;
	
    public Avatar(int unitLength) {
		width = unitLength/2;
		height = unitLength;
		location= new Location(60, 60);
	}
    
    public void draw(Graphics g) {
    	Location loc = this.location;
        g.setColor(Color.red);
        g.fillOval((int)loc.getXLocation(), (int)loc.getYLocation(), width, height);
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

    public void move(String direction) {
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
		if(location.xLocation>60) {
			location.updateLocation(location.xLocation-speed, location.yLocation);
		}
	}
	private void moveRight() {
		if(location.xLocation<500-width) {
			location.updateLocation(location.xLocation+speed, location.yLocation);
		}
	}
	private void moveDown() {
		if(location.yLocation<500-width) {
			location.updateLocation(location.xLocation, location.yLocation+speed);
		}
	}
	private void moveUp() {
		if(location.yLocation>60) {
			location.updateLocation(location.xLocation, location.yLocation-speed);
		}
	}

	public Key pickKey() {
        return null;
    }

    public Powerup activatePowerup() {
        return null;
    }

}
