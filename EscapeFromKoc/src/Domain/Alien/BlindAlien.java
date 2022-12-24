package Domain.Alien;


import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

import Domain.Game.Location;
import java.awt.Color;

import java.awt.Image;

public class BlindAlien implements Alien {

    public int width;
	public int height;
	public String type;
	private Location location;
    private boolean empty;


    public BlindAlien() {
        type = "Blind";
        empty = false;
        width = 25;
        height = 25;
        int coorX = ((ThreadLocalRandom.current().nextInt(9) % 9)+1) * 50 + 10;
        int coorY = ((ThreadLocalRandom.current().nextInt(9) % 9)+1) * 50 + 10;
        location = new Location(coorX, coorY);
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
        g.setColor(Color.MAGENTA);
        g.fillOval((int)loc.getXLocation(), (int)loc.getYLocation(), width, height);
        Image image = new ImageIcon("./EscapeFromKoc/src/UI/Utilities/Images/alien.png").getImage();
        
        g.drawImage(image, (int) location.getXLocation(), (int) location.getYLocation(), 25, 25, null);
    }

    @Override
    public void action() {
        // TODO Auto-generated method stub
        
    }

    @Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
    
}
