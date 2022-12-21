package Domain.Alien;

// If the remaining time is less than 30%, the alien will conclude that the player is doing poorly.
// So, it will change the location of the key only once and disappear.
public class LimitedStrategy implements TimeWastingStrategy {

    @Override
    public void wasteTime() {
        // TODO Auto-generated method stub
        System.out.println("LimitedStrategy");
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "LimitedStrategy";
    }
    
}
