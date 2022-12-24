package Domain.Alien;


// If the remaining time is between 30% - 70%, it will get confused and indecisive.
// So it will stay in the place in which it appears, then disappear after 2 seconds without doing anything.
public class ConfusedStrategy implements TimeWastingStrategy {

    private TimeWastingAlien alien;

    @Override
    public void wasteTime() {
        // TODO Auto-generated method stub
        System.out.println("ConfusedStrategy");
        new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    // your code here
                    alien.setEmpty(true);
                }
            }, 
            2000 
        );

    }

    public TimeWastingAlien getAlien() {
        return this.alien;
    }

    public void setAlien(TimeWastingAlien alien) {
        this.alien = alien;
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "ConfusedStrategy";
    }
   
    
}
