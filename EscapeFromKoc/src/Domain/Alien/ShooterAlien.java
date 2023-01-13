package Domain.Alien;

import Domain.Controllers.GameController;
import Domain.Game.Location;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.Timer;


public class ShooterAlien implements Alien {

	private int width;
	private int height;
	private int size;
	private String type;
	private Location location;
	private Location avatarLocation;
	private boolean isProtectionVestActive;
	private GameController game;	
	private boolean empty;

    @Override
    public String getType() {
        return this.type;
    }
    
    ActionListener shooterActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(game.getAlienController().getAlien() != null){
				if(!game.isPaused() && game.getAlienController().getAlien().getType().equals("Shooter")) {
					System.out.println("SHOOT");
					action();
				}
			}
		}
	};
	
	Timer alienTimer = new Timer(1000, shooterActionListener);

	public ShooterAlien(/*Location avatarLocation, boolean wornProtectionVest*/) {	
        type = "Shooter";
		empty = false;
		game = GameController.getInstance();
        width = game.getGridWidth();
        height = game.getGridHeight();
        size = game.getGridSize();
        location = game.getAvailableLocation(); 
        this.avatarLocation = game.getPlayer().getAvatar().getLocation(); //avatarLocation;
        this.isProtectionVestActive = game.getPlayer().getPlayerState().getIsProtectionVestActive();
        alienTimer.start();
	}
    
    public void draw(Graphics g) {
    	Location loc = this.location;
        g.setColor(Color.CYAN);
        g.fillOval((int)loc.getXLocation(), (int)loc.getYLocation(), size, size);
        Image image = new ImageIcon("./EscapeFromKoc/src/UI/Utilities/Images/alien.png").getImage();
        g.drawImage(image, (int) location.getXLocation(), (int) location.getYLocation(), size, size, null);
    }

    @Override
    public void action() {
    	double distance = Math.abs(avatarLocation.getXLocation() - location.getXLocation()) + Math.abs(avatarLocation.getYLocation() - location.getYLocation());
    	if(distance <= game.getGridSize()*4 && isProtectionVestActive == false) {
    		game.getPlayer().getPlayerState().setHealth(game.getPlayer().getPlayerState().getHealth() - 1);
    		System.out.println(distance);
    		System.out.println(game.getPlayer().getPlayerState().getHealth());	
    	}    
    }

	@Override
	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	
    public Location getLocation() {
		return location;
	}
}
