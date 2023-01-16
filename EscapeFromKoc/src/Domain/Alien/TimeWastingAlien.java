package Domain.Alien;

import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

import Domain.Controllers.AlienController;
import Domain.Controllers.GameController;
import Domain.Game.Location;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Image;

public class TimeWastingAlien implements Alien {

    private int width;
	private int height;
	private int size;
	private String type;
	private Location location;
    private TimeWastingStrategy strategy;
    private boolean empty;
    private GameController game;

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

    public TimeWastingStrategy getStrategy() {
        return this.strategy;
    }

    public TimeWastingStrategy findStrategy(int totalTime, int remainingTime) {
        // Requires: Game is in the running mode.
        // Modifies: TimeWastingStrategy of the TimeWastingAlien.
        // Effects: If strategy has decided, sets the strategy of TimeWastingAlien.
        TimeWastingStrategy strategy;
        if(remainingTime < totalTime * 0.3) {
            strategy = new LimitedStrategy();
        }else if(remainingTime > totalTime * 0.7) {
            strategy = new ChallengingStrategy();
        }else{
            strategy = new ConfusedStrategy();
        }
        return strategy;
    }

    public void setStrategy(TimeWastingStrategy strategy) {
    	strategy.setAlien(this);
        this.strategy = strategy;
    }
    

    public TimeWastingAlien() {
        type = "TimeWasting";
        empty = false;
        game = GameController.getInstance();
        width = game.getGridWidth();
        height = game.getGridHeight();
        size = game.getGridSize();
        location = game.getAvailableLocation();
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
        g.fillOval((int)loc.getXLocation(), (int)loc.getYLocation(), size, size);
        Image image = new ImageIcon("./EscapeFromKoc/src/UI/Utilities/Images/alien.png").getImage();
        
        g.drawImage(image, (int) location.getXLocation(), (int) location.getYLocation(), size, size, null);
        
    }

    @Override
    public void action() {
        // TODO Auto-generated method stub
        strategy.wasteTime();
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
