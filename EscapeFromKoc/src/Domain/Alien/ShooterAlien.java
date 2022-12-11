package Domain.Alien;

import Domain.Game.Location;
import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;


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
        int coorX = ((ThreadLocalRandom.current().nextInt(9) % 9)+1) * 50 + 10;
        int coorY = ((ThreadLocalRandom.current().nextInt(9) % 9)+1) * 50 + 10;
        location = new Location(coorX, coorY);
	}
    
    public void draw(Graphics g) {
    	Location loc = this.location;
        g.setColor(Color.BLUE);
        g.fillOval((int)loc.getXLocation(), (int)loc.getYLocation(), width, height);
    }

    @Override
    public void action() {
        // TODO Auto-generated method stub
        
    }
    
    

}
