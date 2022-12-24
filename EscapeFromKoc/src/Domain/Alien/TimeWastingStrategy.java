package Domain.Alien;

public interface TimeWastingStrategy {
    public void wasteTime();
    public String getType();
    public void setAlien(TimeWastingAlien alien);
}
