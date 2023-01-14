package Domain.GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

import Domain.Game.Location;

public class GameObject {
	
	public int width;
	public int height;
	private int type;
	boolean containsKey;
	String imgdir;
	Location location;
	
    public GameObject(int unitLength) {
		width = unitLength;
		height = unitLength;
		location = new Location(120, 60);
		type = ThreadLocalRandom.current().nextInt(5);
		imgdir = this.setImgDirectory();
		containsKey = false; 
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
	
	public void setContainsKey(boolean containsKey) {
		this.containsKey = containsKey;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	} 
	
	private String setImgDirectory() {
		if(type == 0) {
			return "./EscapeFromKoc/src/UI/Utilities/Images/chair.png";
		}else if(type == 1) {
			return "./EscapeFromKoc/src/UI/Utilities/Images/bookshelf.png";
		}else if(type == 2) {
			return "./EscapeFromKoc/src/UI/Utilities/Images/printer.png";
		}else if(type == 3) {
			return "./EscapeFromKoc/src/UI/Utilities/Images/desk.png";
		}else {
			return "./EscapeFromKoc/src/UI/Utilities/Images/laptop.png";
		}
	}
	
	public String getImageDir() {
		return imgdir;
	}

}
