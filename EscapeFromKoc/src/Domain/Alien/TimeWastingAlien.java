package Domain.Alien;

import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

import Domain.Game.Location;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Image;

public class TimeWastingAlien implements Alien {

    private int width;
	private int height;
	private String type;
	private Location location;
    private String strategy;

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getStrategy() {
        return this.strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
    

    public TimeWastingAlien() {
        type = "TimeWasting";
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
        g.setColor(Color.GREEN);
        g.fillOval((int)loc.getXLocation(), (int)loc.getYLocation(), width, height);
        Image image = new ImageIcon("./EscapeFromKoc/src/UI/Utilities/Images/alien.png").getImage();
        
        g.drawImage(image, (int) location.getXLocation(), (int) location.getYLocation(), 25, 25, null);
        
    }

    @Override
    public void action() {
        // TODO Auto-generated method stub
        
    }
    
}
