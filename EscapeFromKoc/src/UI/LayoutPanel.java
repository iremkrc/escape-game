package UI;

import javax.swing.*;

import Domain.Avatar;
import Domain.GameObject;
import Domain.Controller.GameController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		Avatar avatar = game.getPlayer().getAvatar();
	    avatar.draw(g);
	    LinkedList<GameObject> objectList = game.getObjectList();
	    for(int i=0; i<objectList.size(); i++) { //Hardcoded
	    	GameObject obj1 = objectList.get(0);
	    	GameObject obj2 = objectList.get(1);
	    	obj2.setLocation(170, 60);
	    	obj2.setContainsKey(true);
	    	obj1.draw(g);
	    	obj2.draw(g);
	    }
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(900,600);
    }
}
