package Domain.Controllers;

import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import java.util.Timer;
import java.util.TimerTask;

import Domain.Alien.Alien;
import Domain.Alien.AlienFactory;
import Domain.Alien.ChallengingStrategy;
import Domain.Alien.ShooterAlien;
import Domain.Alien.TimeWastingAlien;
import Domain.Alien.TimeWastingStrategy;

public class AlienController {

    private AlienFactory factory;
    private Alien alien;
    private GameController game = GameController.getInstance();
    private long alienTime = 11;
    private static AlienController instance;

    Timer alienTimer = new Timer();  
	TimerTask tt = new TimerTask() {  
	    @Override  
	    public void run() {  
	    	if(!game.isPaused()) {
				if((alienTime%20 == 0)) {
					createAlienRandomly();
					game.getGameState().setIsBottlePowerupActive(false);
					System.out.println("---------------------------------new-alien-----------------------------");
					if(alien.getType() == "TimeWasting"){
						TimeWastingStrategy s = ((TimeWastingAlien) alien).findStrategy(game.getGameState().getTotalTime(), game.getGameState().getTime());
						((TimeWastingAlien) alien).setStrategy(s);
						if(((TimeWastingAlien) alien).getStrategy().getType() != "ChallengingStrategy"){
							alien.action();
						}
					}
					System.out.println("time left: " + game.getGameState().getTime() + " seconds");
				}
				
				if(alien != null){
					if(alien.getType() == "TimeWasting"){
						TimeWastingStrategy s = ((TimeWastingAlien) alien).findStrategy(game.getGameState().getTotalTime(), game.getGameState().getTime());
						TimeWastingStrategy currentStrategy = ((TimeWastingAlien) alien).getStrategy();
						if(s.getType() != currentStrategy.getType()){
							((TimeWastingAlien) alien).setStrategy(s);
							System.out.println("Strategy changed from " + currentStrategy.getType() + " to "  + s.getType());
							alien.action();
						}
						
					}
				}
				
				if((alien != null) && (alienTime%1 == 0)) {
					if(!game.isPaused() && game.getAlienController().getAlien().getType().equals("Blind")) {
						alien.action();
					}
				}
				
				if((alien != null) && (alienTime%2 == 0)) {
					if(!game.isPaused() && game.getAlienController().getAlien().getType().equals("Shooter")) {
						System.out.println("SHOOT");
						alien.action();
					}
				}
				
				if((alien != null) && (alienTime%6 == 0)) {
					if(!game.isPaused() && game.getAlienController().getAlien().getType().equals("TimeWasting")) {
						if(((TimeWastingAlien) alien).getStrategy().getType() == "ChallengingStrategy"){
							alien.action();
						}
					}
				}
				
				alienTime++;
			}
	    };  
	};  
	
    public AlienController() {
        factory = new AlienFactory();
        System.out.println("alien timer start");
        alienTimer.scheduleAtFixedRate(tt,0,500);
    }

    public static AlienController getInstance() {
		if (instance == null)
			instance = new AlienController();
		return instance;
	}
    
    public Alien getAlien() {
        return alien;
    }

    public void setAlien(Alien alien) {
        this.alien = alien;
    }

    public void createAlienRandomly() {
        List<String> alienTypeList = Arrays.asList("Blind", "Shooter", "TimeWasting");
        int randomTypeIndex = ThreadLocalRandom.current().nextInt(alienTypeList.size()) % alienTypeList.size();
        String randomType = alienTypeList.get(randomTypeIndex);
        this.alien = factory.createAlien(randomType);
    }
    
    public void resetAlienTime() {
    	alienTime = 11;
    }
    
    public void setAlienTime(long alienTime) {
    	this.alienTime = alienTime;
    }
    
    public AlienFactory getAlienFactory() {
    	return this.factory;
    }
    
    public long getAlienTime() {
    	return alienTime;
    }
    
}
