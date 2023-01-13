package Domain.GameObjects.Powerups;
import java.awt.Graphics;

import Domain.Game.Location;

public interface IPowerup {
	public String getType();
	public int getWidth();
	public int getHeight();
	public Location getLocation();
	public void setLocation(double x , double y, int width, int height);
	public void draw(Graphics g);
	public String getImagePath();
	int getSize();
}
