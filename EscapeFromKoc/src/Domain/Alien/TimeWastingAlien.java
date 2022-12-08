package Domain.Alien;

import java.awt.Graphics;

import Domain.Game.Location;
import java.awt.Color;

public class TimeWastingAlien implements Alien {

    public int width;
	public int height;
	public String type;
	Location location;

    public TimeWastingAlien() {
        type = "TimeWasting";
        width = 25;
        height = 25;
        location = new Location(120, 60);
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return this.type;
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        Location loc = this.location;
        g.setColor(Color.GREEN);
        g.fillOval((int)loc.getXLocation(), (int)loc.getYLocation(), width, height);
    }
    
}
