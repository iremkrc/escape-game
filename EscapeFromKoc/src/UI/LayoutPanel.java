package UI;

import javax.swing.*;

import Domain.Avatar;
import Domain.GameObject;
import Domain.Controller.GameController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LayoutPanel extends JPanel {
    GameController game;
	public LayoutPanel(String title) {
        super();
        game = GameController.getInstance();
        setSize(1000, 800);
        setVisible(true);
      		
    }

    public void repaint( Graphics g ) {  
		Avatar avatar = game.getPlayer().getAvatar();
	    avatar.draw(g);
    
    } 
    public void paint( Graphics g ) {  
    for (int x = 50; x <= 500; x += 50 ){
        for (int y = 50; y <= 500; y += 50 ){
            g.drawRect(x, y, 50, 50);
            }
        }
	Avatar avatar = game.getPlayer().getAvatar();
    avatar.draw(g);
    GameObject obj1 = game.getObj1();
    GameObject obj2 = game.getObj2();
    obj1.draw(g);
    obj2.draw(g);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(900,600);
    }
}
