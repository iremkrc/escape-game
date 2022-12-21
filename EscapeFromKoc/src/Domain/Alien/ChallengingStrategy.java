package Domain.Alien;

// If more than 70% of the total time remains, the alien will conclude that the player is doing well. 
// So it will make the situation challenging by changing the location of the key every 3 seconds
public class ChallengingStrategy implements TimeWastingStrategy {

    @Override
    public void wasteTime() {
        // TODO Auto-generated method stub
        System.out.println("ChallengingStrategy");
    }
    
}
