package Domain.Alien;

import Domain.Game.Location;
import java.awt.Color;
import java.awt.Graphics;


public class ShooterAlien implements Alien {

	public int width;
	public int height;
	public String type;
	Location location;

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return this.type;
    }

    public ShooterAlien() {
        type = "Shooter";
		width = 25;
		height = 25;
		location = new Location(120, 60); 
	}
    
    public void draw(Graphics g) {
    	Location loc = this.location;
        g.setColor(Color.BLUE);
        g.fillOval((int)loc.getXLocation(), (int)loc.getYLocation(), width, height);
    }
    
    

}
