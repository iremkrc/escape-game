package UI;

import javax.swing.*;

import Domain.GameObjects.GameObject;
import Domain.GameObjects.Powerups.IPowerup;
import Domain.Alien.Alien;
import Domain.Controllers.GameController;
import Domain.Controllers.PowerupController;
import Domain.Game.GameKeyListener;
import Domain.Game.GameMouseListener;
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
        GameMouseListener mlisteners = new GameMouseListener(game);
		addMouseListener(mlisteners);		
    }

    public void paint( Graphics g ) {  
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
        if(powerup != null){
            powerup.draw(g);
        }

        Alien alien = game.getAlienController().getAlien();
        if(alien != null){
            if(!alien.isEmpty()){
                alien.draw(g);
            }
        }
        
        Avatar avatar = game.getPlayer().getAvatar();
    	Location loc = avatar.getLocation();
        Image image = new ImageIcon("./EscapeFromKoc/src/UI/Utilities/Images/avatar.png").getImage();
        g.drawImage(image, (int) loc.getXLocation(), (int) loc.getYLocation(), avatar.getWidth(), avatar.getHeight(), null);
        
        
        g.setColor(Color.black); 
    	for (int x = game.getGridSize(); x <= game.getGridSize()*game.getGridWidth(); x += game.getGridSize() ){
	        for (int y = game.getGridSize(); y <= game.getGridSize()*game.getGridHeight(); y += game.getGridSize() ){
	            g.drawRect(x, y, game.getGridSize(), game.getGridSize());
	        }
	    }
    	
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }
}
