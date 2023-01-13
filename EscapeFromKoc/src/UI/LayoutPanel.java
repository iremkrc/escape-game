package UI;

import javax.swing.*;

import Domain.GameObjects.GameObject;
import Domain.GameObjects.Powerups.IPowerup;
import Domain.Alien.Alien;
import Domain.Controllers.GameController;
import Domain.Controllers.PowerupController;
import Domain.Game.GameKeyListener;
import Domain.Game.GameMouseListener;
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

    public void repaint( Graphics g ) {  
		game.getPlayer().getAvatar().draw(g);
    } 
    public void paint( Graphics g ) {  
    	
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
        
        g.setColor(Color.black); 
    	for (int x = game.getGridSize(); x <= game.getGridSize()*game.getGridWidth(); x += game.getGridSize() ){
	        for (int y = game.getGridSize(); y <= game.getGridSize()*game.getGridHeight(); y += game.getGridSize() ){
	            g.drawRect(x, y, game.getGridSize(), game.getGridSize());
	        }
	    }
    	
        if(game.getGameState().getHintActive()){
            g.setColor(Color.RED);
            g.drawRect((int) game.getHintLocation().xLocation,(int) game.getHintLocation().yLocation, 4*game.getGridSize(), 4*game.getGridSize());
            g.setColor(Color.black); 
        }
	    
    	LinkedList<GameObject> objectList = game.currentBuilding.getObjectList();
	    for(int i=0; i<objectList.size(); i++) { //Hardcoded
	    	GameObject obj = objectList.get(i);
	    	obj.draw(g);
	    }

        Avatar avatar = game.getPlayer().getAvatar();
	    avatar.draw(g);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }
}
