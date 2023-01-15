package Domain.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.Timer;

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
    private int alienTime = 8;
    private static AlienController instance;

    ActionListener shooterActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!game.isPaused()) {
				if((alienTime%20 == 0)) {
					createAlienRandomly();
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
		}
	};
	
    private Timer alienTimer = new Timer(500, shooterActionListener);

	
	
    public AlienController() {
        factory = new AlienFactory();
        alienTimer.start();
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
    	alienTime = 8;
    }
}
