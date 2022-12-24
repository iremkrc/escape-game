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
	private String type;
	private Location location;
	private Location avatarLocation;
	private boolean isProtectionVestActive;
	private GameController escapeFromKocGame;	
	private boolean empty;

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return this.type;
    }
    
    ActionListener shooterActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(escapeFromKocGame.getAlienController().getAlien() != null){
				if(!escapeFromKocGame.isPaused() && escapeFromKocGame.getAlienController().getAlien().getType().equals("Shooter")) {
					System.out.println("SHOOT");
					action();
				}
			}
		}
	};
	
	Timer alienTimer = new Timer(1000, shooterActionListener);

	public ShooterAlien(/*Location avatarLocation, boolean wornProtectionVest*/) {
    	escapeFromKocGame = GameController.getInstance();	
        type = "Shooter";
		empty = false;
		width = 25;
		height = 25;
        int coorX = ((ThreadLocalRandom.current().nextInt(9) % 9)+1) * 50 + 10;
        int coorY = ((ThreadLocalRandom.current().nextInt(9) % 9)+1) * 50 + 10;
        location = new Location(coorX, coorY);
        this.avatarLocation = escapeFromKocGame.getPlayer().getAvatar().getLocation(); //avatarLocation;
        this.isProtectionVestActive = escapeFromKocGame.getPlayer().getPlayerState().getIsProtectionVestActive();
        alienTimer.start();
	}
    
    public void draw(Graphics g) {
    	Location loc = this.location;
        g.setColor(Color.CYAN);
        g.fillOval((int)loc.getXLocation(), (int)loc.getYLocation(), width, height);

        Image image = new ImageIcon("./EscapeFromKoc/src/UI/Utilities/Images/alien.png").getImage();
        
        g.drawImage(image, (int) location.getXLocation(), (int) location.getYLocation(), 25, 25, null);


    }

    @Override
    public void action() {
        // TODO Auto-generated method stub
    	double distance = Math.abs(avatarLocation.getXLocation() - location.getXLocation()) + Math.abs(avatarLocation.getYLocation() - location.getYLocation());
    	if(distance <= 50*4 && isProtectionVestActive == false) {
    		escapeFromKocGame.getPlayer().getPlayerState().setHealth(escapeFromKocGame.getPlayer().getPlayerState().getHealth() - 1);
    		System.out.println(distance);
    		System.out.println(escapeFromKocGame.getPlayer().getPlayerState().getHealth());
    		
    	}
        
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
