package Domain.Alien;

import javax.swing.Action;
import javax.swing.Timer;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Domain.Controllers.GameController;
import Domain.Game.Building;

// If more than 70% of the total time remains, the alien will conclude that the player is doing well. 
// So it will make the situation challenging by changing the location of the key every 3 seconds
public class ChallengingStrategy implements TimeWastingStrategy {

    private TimeWastingAlien alien;
    private GameController game;
    private Timer timer;

    @Override
    public void wasteTime() {
        // TODO Auto-generated method stub
        System.out.println("ChallengingStrategy");
        timer = new Timer(3000, alienListener);
		timer.start();
    }

    ActionListener alienListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
            changeKeyLocation();
        }
    };

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "ChallengingStrategy";
    }

    @Override
    public void setAlien(TimeWastingAlien alien) {
        // TODO Auto-generated method stub
        this.alien = alien;
    }

    public void stopTimer(){
        timer.stop();
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
