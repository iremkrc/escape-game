package UI;

import javax.swing.*;
import java.awt.*;


public class Layout extends JFrame {
    public Layout() {
        setSize(1000, 800);
        setVisible(true);
    }

    public void paint( Graphics g )    
 {  
    for (int x = 50; x <= 500; x += 50 ){
        for (int y = 50; y <= 500; y += 50 ){
            g.drawRect(x, y, 50, 50);
        }

    }
 } 
}
