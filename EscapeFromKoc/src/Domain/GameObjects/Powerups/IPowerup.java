package Domain.GameObjects.Powerups;

import Domain.Game.Location;

public interface IPowerup {
	public String getType();
	public void activatePowerup();
	public int getWidth();
	public int getHeight();
	public Location getLocation();
	public void setLocation(double x , double y, int width, int height);
	public void move();
}
