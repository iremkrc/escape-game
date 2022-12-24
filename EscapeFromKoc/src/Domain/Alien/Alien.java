package Domain.Alien;
import java.awt.Graphics;


public interface Alien {

    public String getType();
    public void draw(Graphics g);
    public void action();
    public boolean isEmpty();
    public void setEmpty(boolean empty);
}
