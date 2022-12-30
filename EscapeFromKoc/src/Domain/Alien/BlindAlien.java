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
	public String type;
	private Location location;
    private boolean empty;
    private Location avatarLocation;
	private boolean isBottlePowerupActive;
    private double speed = 50;
	GameController escapeFromKocGame;

    ActionListener blindAlienActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!escapeFromKocGame.isPaused() && escapeFromKocGame.getAlienController().getAlien().getType().equals("Blind")) {
				System.out.println("Blind");
				action();
			}
		}
	};

	Timer blindAlienTimer = new Timer(1000, blindAlienActionListener);

    public BlindAlien() {
        escapeFromKocGame = GameController.getInstance();
        type = "Blind";
        empty = false;
        width = 25;
        height = 25;
        int coorX = ((ThreadLocalRandom.current().nextInt(9) % 9)+1) * 50 + 10;
        int coorY = ((ThreadLocalRandom.current().nextInt(9) % 9)+1) * 50 + 10;
        location = new Location(coorX, coorY);
        this.avatarLocation = escapeFromKocGame.getPlayer().getAvatar().getLocation(); 
        this.isBottlePowerupActive = escapeFromKocGame.getPlayer().getPlayerState().getIsBottlePowerupActive();
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
        g.fillOval((int)loc.getXLocation(), (int)loc.getYLocation(), width, height);
        Image image = new ImageIcon("./EscapeFromKoc/src/UI/Utilities/Images/alien.png").getImage();
        g.drawImage(image, (int) location.getXLocation(), (int) location.getYLocation(), 25, 25, null);
    }

    @Override
    public void action() {
        double xDistance = Math.abs(avatarLocation.getXLocation() - location.getXLocation());
    	double yDistance = Math.abs(avatarLocation.getYLocation() - location.getYLocation());
        
        System.out.println(escapeFromKocGame.getGameState().getIsBottlePowerupActive());
        if(escapeFromKocGame.getGameState().getIsBottlePowerupActive()==false) {
            //alien randomly moves 
    		moveRandomly();
            if(xDistance < 20 && yDistance < 20){
                escapeFromKocGame.getPlayer().getPlayerState().setHealth(0);
            }
    	}if(escapeFromKocGame.getGameState().getIsBottlePowerupActive()==true){
            //alien goes in the direction of plastic bottle powerup
            System.out.println(escapeFromKocGame.getBottlePowerupDirection());
            moveToDirection(escapeFromKocGame.getBottlePowerupDirection());
            if(xDistance < 20 && yDistance < 20){
                escapeFromKocGame.getPlayer().getPlayerState().setHealth(0);
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
		if(location.xLocation>60) {
			location.updateLocation(location.xLocation-speed, location.yLocation);
		}
	}
	public void moveRight() {
		if(location.xLocation<500-width) {
			location.updateLocation(location.xLocation+speed, location.yLocation);
		}
	}
	public void moveDown() {
		if(location.yLocation<500-width) {
			location.updateLocation(location.xLocation, location.yLocation+speed);
		}
	}
	public void moveUp() {
		if(location.yLocation>60) {
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

