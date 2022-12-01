package Domain;

import java.awt.Color;
import java.awt.Graphics;

public class GameObject {
	
	public int width;
	public int height;
	int angle;
	boolean containsKey;
	public String type;
	Location location;
	
    public GameObject(int unitLength) {
		width = unitLength/2;
		height = unitLength;
		location= new Location(60, 60);
	}
    
    public void draw(Graphics g) {
    	Location loc = this.location;
        g.setColor(Color.black);
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

	public boolean isContainsKey() {
		return containsKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
    

}
