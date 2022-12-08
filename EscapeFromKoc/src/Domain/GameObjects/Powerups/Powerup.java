package Domain.GameObjects.Powerups;

import java.util.Random;
import Domain.Controllers.GameController;
import Domain.Game.Location;

public abstract class Powerup implements IPowerup {
    
    public String type;
	public String imagePath;
	public Location location;
	private int width;
	private int height;
	private double speed;
	public GameController escapeFromKocGame = GameController.getInstance();
	Random rand = new Random();

	public Powerup(int unitLength) {
		width = unitLength/4;
		height = unitLength/4;
        location = new Location(0, 0);
    }
	
    @Override
    public String getType() {
        return type;
    }

    @Override
    public void activatePowerup() {
        
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(double x, double y, int width, int height) {
        location.updateLocation(x, y);
    }

    @Override
    public void move() {
        
    }

    public String getImagePath() {
		return imagePath;
	}
    
}
