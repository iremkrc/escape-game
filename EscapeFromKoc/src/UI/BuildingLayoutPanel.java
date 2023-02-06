package UI;

import javax.swing.*;

import Domain.Player.Avatar;
import Domain.Game.Door;
import Domain.Game.Location;
import Domain.GameObjects.GameObject;
import Domain.Controllers.GameController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class BuildingLayoutPanel extends JPanel {
    GameController game;
	private static final Color BACKGROUND_COLOR = new Color(255, 255, 255);

	public BuildingLayoutPanel(String title) {
        super();
        game = GameController.getInstance();
        setSize(1800, 1500);
        setVisible(true);
		setBackground(BACKGROUND_COLOR);
        GameMouseListener mlisteners = new GameMouseListener(game);
		addMouseListener(mlisteners);
    }

    public void repaint( Graphics g ) {  
    	LinkedList<GameObject> objectList = game.currentBuilding.getObjectList();
	    for(int i=0; i<objectList.size(); i++) { //Hardcoded
	    	GameObject obj = objectList.get(i);
	    	obj.draw(g);
	    }
    } 
    
    public void paint( Graphics g ) {  
    	LinkedList<GameObject> objectList = game.currentBuilding.getObjectList();
	    for(int i=0; i<objectList.size(); i++) { //Hardcoded
	    	GameObject obj = objectList.get(i);
	    	Location loc = obj.getLocation();
	        //g.setColor(Color.lightGray);
	        //g.fillRect((int)loc.getXLocation(), (int)loc.getYLocation(), obj.getWidth(), obj.getHeight());
	        g.setColor(Color.BLACK);
	        Image image = new ImageIcon(obj.getImageDir()).getImage();
	        g.drawImage(image, (int) loc.getXLocation(), (int) loc.getYLocation(), obj.getWidth(), obj.getHeight(), null);
	    }
	    
	    Door door = game.currentBuilding.getDoor();
	    Image doorImage = new ImageIcon(game.currentBuilding.getDoor().getDoorImgDirectory()).getImage();
        g.drawImage(doorImage, (int) door.getLocation().getXLocation(), (int) door.getLocation().getYLocation(), door.getWidth(), door.getHeight(), null);
  
	    for (int x = game.getGridSize(); x <= game.getGridSize()*game.getGridWidth(); x += game.getGridSize() ){
	        for (int y = game.getGridSize(); y <= game.getGridSize()*game.getGridHeight(); y += game.getGridSize() ){
	            g.drawRect(x, y, game.getGridSize(), game.getGridSize());
	        }
	    }
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(1000,600);
    }
}
