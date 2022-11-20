package Domain.Game;

public class Avatar {

    public int width;
	public int height;
	int angle;
	double speed;
	Location location;
	
    public Avatar(int unitLength) {
		width = unitLength/2;
		height = unitLength;
		angle = 0;
		//speed =
		location= new Location(0, unitLength*10-height);
	}
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getAngle() {
		return angle;
	}

	public Location getLocation() {
		return location;
	}

	public void setXLocation(double xLocation) {
		location.xLocation=xLocation;
	}

    public void setYLocation(double yLocation) {
		location.yLocation=yLocation;
	}

	public void setAngle(int angle) {
		this.angle=angle;
	}

    public void move(String direction) {
    }  

    public Key pickKey() {
        return null;
    }

	private void moveLeft() {

	}

	private void moveRight() {
		  
	}

	public void rotate(String direction) {
		if(direction.equals("right")) {
			rotateRight();
		}else {
			rotateLeft();
		}
	}

	private void rotateLeft() {
		if(angle>=-80) {
			angle-=10;
		}else {
			angle=-90;
		}
	}

	private void rotateRight() {
		if(angle<=80) {
			angle+=10;
		}else {
			angle=90;
		}	
	}

    public Powerup activatePowerup() {
        return null;
    }

}
