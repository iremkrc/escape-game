package UI;

import javax.imageio.ImageIO;
import javax.swing.*;

import Domain.GameObjects.GameObject;
import Domain.GameObjects.Powerups.IPowerup;
import Domain.Controllers.GameController;
import Domain.Controllers.PowerupController;
import Domain.Game.GameKeyListener;
import Domain.Player.Avatar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class LayoutPanel extends JPanel {
    GameController game;
	public LayoutPanel(String title) {
        super();
        game = GameController.getInstance();
        setSize(1000, 800);
        setVisible(true);
		GameKeyListener listeners = new GameKeyListener(game);
		addMouseListener(listeners);
    }

    public void repaint( Graphics g ) {  
		game.getPlayer().getAvatar().draw(g);
    } 

  
    public void paint( Graphics g ) {  
	    for (int x = 50; x <= 500; x += 50 ){
	        for (int y = 50; y <= 500; y += 50 ){
	            g.drawRect(x, y, 50, 50);
            }
        }
	    
    	LinkedList<GameObject> objectList = game.currentBuilding.getObjectList();
	    for(int i=0; i<objectList.size(); i++) { //Hardcoded
	    	GameObject obj = objectList.get(i);
	    	obj.draw(g);
	    }
		Avatar avatar = game.getPlayer().getAvatar();
	    avatar.draw(g);

        IPowerup powerup = game.getPowerupController().getPowerup();
        powerup.draw(g);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(900,600);
    }
}
