package Domain.Alien;

import Domain.Game.Location;


public interface Alien {

    public String getType();
    public void action();
    public boolean isEmpty();
    public void setEmpty(boolean empty);
    public Location getLocation();
    public void setLocation(Location loc);
    public int getSize();
}
