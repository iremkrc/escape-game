package Domain.Alien;
import java.awt.Graphics;

import Domain.Game.Location;


public interface Alien {

    public String getType();
    public void draw(Graphics g);
    public void action();
    public boolean isEmpty();
    public void setEmpty(boolean empty);
    public Location getLocation();
}
