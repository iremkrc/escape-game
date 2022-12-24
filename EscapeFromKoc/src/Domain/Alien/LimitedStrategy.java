package Domain.Alien;

import java.util.concurrent.ThreadLocalRandom;

import Domain.Controllers.GameController;
import Domain.Game.Building;

// If the remaining time is less than 30%, the alien will conclude that the player is doing poorly.
// So, it will change the location of the key only once and disappear.
public class LimitedStrategy implements TimeWastingStrategy {

    private TimeWastingAlien alien;
    private GameController game;

    @Override
    public void wasteTime() {
        // TODO Auto-generated method stub
        System.out.println("LimitedStrategy");
        new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    // your code here
                    changeKeyLocation();
                    alien.setEmpty(true);
                }
            }, 
            1000 
        );
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "LimitedStrategy";
    }

    @Override
    public void setAlien(TimeWastingAlien alien) {
        // TODO Auto-generated method stub
        this.alien = alien;
        
    }

    public void changeKeyLocation(){
        game = GameController.getInstance();
        Building currentBuilding = game.getCurrentBuilding();
        int keyLocation = game.getBuildingKeyMap().get(currentBuilding.getBuildingName());
        int objectCount = currentBuilding.getIntendedObjectCount();
        int newKeyLocation = ThreadLocalRandom.current().nextInt(0, objectCount);
        currentBuilding.getObjectList().get(keyLocation).setContainsKey(false);
        currentBuilding.getObjectList().get(newKeyLocation).setContainsKey(true);
        game.getBuildingKeyMap().put(currentBuilding.getBuildingName(), newKeyLocation);
        System.out.println("Key location changed from " + keyLocation + " to " + newKeyLocation);
    }
    
}
