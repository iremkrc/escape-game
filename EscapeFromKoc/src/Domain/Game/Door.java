package Domain.Game;

import java.util.concurrent.ThreadLocalRandom;

public class Door {
	private int width;
	private int height;
	private boolean isOpen;
	private Location location;

	public Door(int unitLength, int xlocation, int ylocation) {
		width = unitLength;
		height = unitLength;
		location = new Location(xlocation, ylocation);
		isOpen = false; 
	}
	
	public boolean getIsOpen() {
		return isOpen;
	}
	
	public void setIsOpen(boolean flag) {
		isOpen = flag;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public String getDoorImgDirectory() {
		if(isOpen) {
			return "./EscapeFromKoc/src/UI/Utilities/Images/openDoor.png";
		}else {
			return "./EscapeFromKoc/src/UI/Utilities/Images/closedDoor.png";
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
