package Domain.Game;

import java.util.Random;

import Domain.Controllers.GameController;
import Domain.GameObjects.Powerups.BottlePowerup;
import Domain.GameObjects.Powerups.HintPowerup;
import Domain.GameObjects.Powerups.IPowerup;
import Domain.GameObjects.Powerups.LifePowerup;
import Domain.GameObjects.Powerups.TimePowerup;
import Domain.GameObjects.Powerups.VestPowerup;

public class PowerupFactory {
    
    private static PowerupFactory instance;
	private PowerupFactory() {}
    private boolean powerupExists = false;
    GameController game = GameController.getInstance();

	public static PowerupFactory getInstance() {
		if (instance == null) {
			instance = new PowerupFactory();
		}
		return instance;
	}

    public IPowerup getPowerupType(int type){
		int length = 0;
        switch (type) {
			case 0: 
                return new TimePowerup(length);
			case 1: 
                return new HintPowerup(length);
			case 2: 
                return new VestPowerup(length);
			case 3: 
                return new BottlePowerup(length);
            case 4: 
                return new LifePowerup(length);
			default: 
                throw new IllegalArgumentException("Unknown type "+ type);
		}
	}

    public IPowerup getPowerupType(String type){
            if (type == null || type.isEmpty())
                return null;
            int length = 0;
            switch (type) {
            case "time":
                return new TimePowerup(length);
            case "hint":
                return new HintPowerup(length);
            case "vest":
                return new VestPowerup(length);
            case "bottle":
                return new BottlePowerup(length);
            case "life":
                return new LifePowerup(length);
            default:
                throw new IllegalArgumentException("Unknown type "+ type);
            }
    }

    public IPowerup createRandomPowerup(){
        Random rand = new Random();
		int type = rand.nextInt(4);
        IPowerup newPowerup;

		if (powerupExists) {
			type = rand.nextInt(4);
            newPowerup = getPowerupType(type);
			return newPowerup;
		}else {
			return null;
		}
    }

}
