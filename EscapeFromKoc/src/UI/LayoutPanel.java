package UI;

import javax.swing.*;

import Domain.GameObjects.GameObject;
import Domain.GameObjects.Powerups.IPowerup;
import Domain.Alien.Alien;
import Domain.Controllers.GameController;
import Domain.Controllers.PowerupController;
import Domain.Game.Door;
import Domain.Game.Location;
import Domain.Player.Avatar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;


public class LayoutPanel extends JPanel {
    GameController game;
	public LayoutPanel(String title) {
        super();
        game = GameController.getInstance();
        //setSize(2000, 1500);
        setVisible(true);
        setBackground(Color.WHITE);
        GameMouseListener mlisteners = new GameMouseListener(game);
		addMouseListener(mlisteners);		
    }

    public void paint( Graphics g ) {  
        Alien alien = game.getAlienController().getAlien();
        Location alienLoc = new Location(0,0);
        if(alien != null){
        	alienLoc = alien.getLocation();
            if(!alien.isEmpty()) {
            	if(alien.getType()=="Shooter") {
            		g.setColor(Color.darkGray);
            		g.fillRect((int)alienLoc.getXLocation()-3*game.getGridSize(), (int)alienLoc.getYLocation()-3*game.getGridSize(), 7*game.getGridSize(), 7*game.getGridSize());
            	}
            }
        }
        
        if(alien != null){
            if(!alien.isEmpty()) {
            	alienLoc = alien.getLocation();
            	Image AlienImage = new ImageIcon("./EscapeFromKoc/src/UI/Utilities/Images/alien.png").getImage();;
            	
            	if(alien.getType()=="TimeWasting") {
            		g.setColor(Color.GREEN);
            	}else if(alien.getType()=="Blind") {
                    g.setColor(Color.MAGENTA);
            	}else if(alien.getType()=="Shooter") {
            		g.setColor(Color.CYAN);
            	}
            	g.fillOval((int)alienLoc.getXLocation(), (int)alienLoc.getYLocation(), alien.getSize(), alien.getSize());
            	g.drawImage(AlienImage, (int) alienLoc.getXLocation(), (int) alienLoc.getYLocation(), alien.getSize(), alien.getSize(), null);
            }
        }
        
        if(game.getGameState().getHintActive()){
            g.setColor(Color.RED);
            g.fillRect((int) game.getHintLocation().xLocation,(int) game.getHintLocation().yLocation, 4*game.getGridSize(), 4*game.getGridSize());
            //g.drawRect((int) game.getHintLocation().xLocation,(int) game.getHintLocation().yLocation, 4*game.getGridSize(), 4*game.getGridSize());
            g.setColor(Color.black); 
        }
        
        
        
        LinkedList<GameObject> objectList = game.currentBuilding.getObjectList();
	    for(int i=0; i<objectList.size(); i++) { //Hardcoded
	    	GameObject obj = objectList.get(i);
	    	Location loc = obj.getLocation();
	        //g.setColor(Color.lightGray);
	        //g.fillRect((int)loc.getXLocation(), (int)loc.getYLocation(), obj.getWidth(), obj.getHeight());
	        //g.setColor(Color.black);
	        Image image = new ImageIcon(obj.getImageDir()).getImage();
	        g.drawImage(image, (int) loc.getXLocation(), (int) loc.getYLocation(), obj.getWidth(), obj.getHeight(), null);
	    }
	    	    
        IPowerup powerup = game.getPowerupController().getPowerup();
        Location loc = new Location(0,0);
        if(powerup != null){
        	loc = powerup.getLocation();
            Image powerupImage = new ImageIcon(powerup.getImagePath()).getImage();
            g.drawImage(powerupImage, (int)loc.getXLocation(), (int)loc.getYLocation(), powerup.getSize(), powerup.getSize(), null);

        }
        
                
        Avatar avatar = game.getPlayer().getAvatar();
    	loc = avatar.getLocation();
        Image image = new ImageIcon("./EscapeFromKoc/src/UI/Utilities/Images/avatar.png").getImage();
        g.drawImage(image, (int) loc.getXLocation(), (int) loc.getYLocation(), avatar.getWidth(), avatar.getHeight(), null);
        
        g.setColor(Color.black); 
    	for (int x = game.getGridSize(); x <= game.getGridSize()*game.getGridWidth(); x += game.getGridSize() ){
	        for (int y = game.getGridSize(); y <= game.getGridSize()*game.getGridHeight(); y += game.getGridSize() ){
	            g.drawRect(x, y, game.getGridSize(), game.getGridSize());
	        }
	    }
    	
    	Door door = game.currentBuilding.getDoor();
	    Image doorImage = new ImageIcon(game.currentBuilding.getDoor().getDoorImgDirectory()).getImage();
        g.drawImage(doorImage, (int) door.getLocation().getXLocation(), (int) door.getLocation().getYLocation(), door.getWidth(), door.getHeight(), null);

        if(game.getKeyFoundBool()) {
        	image = new ImageIcon("./EscapeFromKoc/src/UI/Utilities/Images/key.png").getImage();
            g.drawImage(image, (int) loc.getXLocation()+avatar.getWidth()/2, (int) loc.getYLocation()+avatar.getWidth()/2, avatar.getWidth(), Math.round(avatar.getHeight()/2), null);
            
        }
    	
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }
}
