package Domain.Controllers;

import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Timer;

import Domain.GameObjects.Powerups.PowerupFactory;
import Domain.Alien.TimeWastingAlien;
import Domain.Alien.TimeWastingStrategy;
import Domain.GameObjects.Powerups.IPowerup;

public class PowerupController {
    PowerupFactory factory;
    IPowerup powerup;
    private GameController game = GameController.getInstance();
    private final int powerupTimersec = 1000;
    private int PowerupCounterTime = 1;
    private boolean powerupBoolean = false;
    private int hintTime = 0;
    private int bottleTime = 0;
    private int vestTime = 0;

    Timer powerupTimer = new Timer();  
	TimerTask tt = new TimerTask() {  
	    @Override  
	    public void run() {  
	    	if(!game.isPaused()) {
				if(PowerupCounterTime == 6){
					IPowerup powerupx = createPowerupRandomly();
					setPowerup(powerupx);
					PowerupCounterTime = 0;
					powerupBoolean = !powerupBoolean;
				}
				if(!powerupBoolean){
					setPowerup(null);
				}
				
				if(game.getGameState().getHintActive()){
					hintTime++;
				}else {
					hintTime = 0;
				}
				
				if(hintTime == 10*1000/powerupTimersec) {
					game.getGameState().setHintActive(false);
					hintTime = 0;
				}
				
				if(game.getGameState().getIsBottlePowerupActive()){
					bottleTime++;
				}else {
					bottleTime = 0;
				}
				
				if(bottleTime == 10*1000/powerupTimersec) {
					game.getGameState().setIsBottlePowerupActive(false);
					bottleTime = 0;
				}
				
				if(game.getGameState().getIsVestPowerupActive()){
					vestTime++;
				}
				if(vestTime == 20*1000/powerupTimersec) {
					game.getGameState().setIsVestPowerupActive(false);
					vestTime = 0;
				}
			}
			PowerupCounterTime++;
	    };  
	};  
    
    public PowerupController() {
        factory = new PowerupFactory();
        System.out.println("powerup timer start");
        powerupTimer.scheduleAtFixedRate(tt,0,powerupTimersec);
    }

    public IPowerup getPowerup(){
        return powerup;
    }

    public void setPowerup(IPowerup powerup){
        this.powerup = powerup;
    }

    public void deletePowerup(IPowerup powerup){
        this.powerup = null;
    }

    public IPowerup createPowerupRandomly() {
        List<String> powerupTypeList = Arrays.asList("time", "hint", "vest", "bottle", "life");
        int randomTypeIndex = ThreadLocalRandom.current().nextInt(powerupTypeList.size()) % powerupTypeList.size();
        String randomType = powerupTypeList.get(randomTypeIndex);
        IPowerup powerup = factory.createPowerup(randomType);
        System.out.println(randomType);
        return powerup;
    }
    
    public void resetPowerupTime() {
    	PowerupCounterTime = 1;
    	powerupBoolean = false;
    }
}
