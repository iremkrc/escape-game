package Domain.Alien;


import java.awt.Graphics;
import Domain.Controllers.GameController;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import Domain.Game.Location;
import java.awt.Color;
import java.awt.Image;
import javax.swing.Timer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BlindAlien implements Alien {

    public int width;
	public int height;
	public int size;
	public String type;
	private Location location;
    private boolean empty;
    private Location avatarLocation;
	private boolean isBottlePowerupActive;
    private double speed;
    private GameController game;

    ActionListener blindAlienActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(game.getAlienController().getAlien() != null) {
				if(!game.isPaused() && game.getAlienController().getAlien().getType().equals("Blind")) {
					System.out.println("Blind");
					action();
				}
			}
		}
	};

	Timer blindAlienTimer = new Timer(500, blindAlienActionListener);

    public BlindAlien() {
        type = "Blind";
        empty = false;
        game = GameController.getInstance();
        width = game.getGridWidth();
        height = game.getGridHeight();
        size = game.getGridSize();
        speed = game.getGridSize();
        int Xloc = ((ThreadLocalRandom.current().nextInt(width-1) % (width-1))+1) * size;
        int Yloc = ((ThreadLocalRandom.current().nextInt(height-1) % (height-1))+1) * size;
        location = new Location(Xloc, Yloc);
        this.avatarLocation = game.getPlayer().getAvatar().getLocation(); 
        this.isBottlePowerupActive = game.getPlayer().getPlayerState().getIsBottlePowerupActive();
        blindAlienTimer.start();
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void draw(Graphics g) {
        Location loc = this.location;
        g.setColor(Color.MAGENTA);
        g.fillOval((int)loc.getXLocation(), (int)loc.getYLocation(), size, size);
        Image image = new ImageIcon("./EscapeFromKoc/src/UI/Utilities/Images/alien.png").getImage();
        g.drawImage(image, (int) location.getXLocation(), (int) location.getYLocation(), size, size, null);
    }

    @Override
    public void action() {
        double xDistance = Math.abs(avatarLocation.getXLocation() - location.getXLocation());
    	double yDistance = Math.abs(avatarLocation.getYLocation() - location.getYLocation());
        
        System.out.println(game.getGameState().getIsBottlePowerupActive());
        if(game.getGameState().getIsBottlePowerupActive()==false) {
            //alien randomly moves 
    		moveRandomly();
            if(xDistance < 20 && yDistance < 20){
            	game.getPlayer().getPlayerState().setHealth(0);
            }
    	}if(game.getGameState().getIsBottlePowerupActive()==true){
            //alien goes in the direction of plastic bottle powerup
            System.out.println(game.getBottlePowerupDirection());
            moveToDirection(game.getBottlePowerupDirection());
            if(xDistance < 20 && yDistance < 20){
            	game.getPlayer().getPlayerState().setHealth(0);
            }    
        }       
    }

    @Override
	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}  

    public void moveLeft() {
		if(location.xLocation>game.getGridSize()) {
			location.updateLocation(location.xLocation-speed, location.yLocation);
		}
	}
	public void moveRight() {
		int maxWidth = game.getGridSize()*game.getGridWidth();
		if(location.xLocation<maxWidth-width) {
			location.updateLocation(location.xLocation+speed, location.yLocation);
		}
	}
	public void moveDown() {
		int maxHeight = game.getGridSize()*game.getGridHeight();
		if(location.yLocation<maxHeight-width) {
			location.updateLocation(location.xLocation, location.yLocation+speed);
		}
	}
	public void moveUp() {
		if(location.yLocation>game.getGridSize()) {
			location.updateLocation(location.xLocation, location.yLocation-speed);
		}
	}

    public void moveRandomly(){
        List<String> directionsList = Arrays.asList("South", "North", "West", "East");
        int randomTypeIndex = ThreadLocalRandom.current().nextInt(directionsList.size()) % directionsList.size();
        String randomDirection = directionsList.get(randomTypeIndex);
        moveToDirection(randomDirection);
    }

    public void moveToDirection(String direction){
        if(direction.equals("East")) {
			moveRight();
		}
    	else if(direction.equals("West")){
			moveLeft();
		}
		else if(direction.equals("North")){
			moveUp();
		}
		else if(direction.equals("South")){
			moveDown();
		}
    }
}

