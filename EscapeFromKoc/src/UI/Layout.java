package UI;

import javax.swing.*;

import Domain.Game.Avatar;
import Domain.Game.EscapeFromKocGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Layout extends JPanel {
    EscapeFromKocGame game;
	public Layout(String title) {
        super();
        game = EscapeFromKocGame.getInstance();
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
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(900,600);
    }
}
