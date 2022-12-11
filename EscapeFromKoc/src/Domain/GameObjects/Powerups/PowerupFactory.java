package Domain.GameObjects.Powerups;

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
	public PowerupFactory() {}
    private boolean powerupExists = false;
    GameController game = GameController.getInstance();

	public static PowerupFactory getInstance() {
		if (instance == null) {
			instance = new PowerupFactory();
		}
		return instance;
	}

    public IPowerup createPowerup(int type){
        switch (type) {
			case 0: 
                return new TimePowerup();
			case 1: 
                return new HintPowerup();
			case 2: 
                return new VestPowerup();
			case 3: 
                return new BottlePowerup();
            case 4: 
                return new LifePowerup();
			default: 
                throw new IllegalArgumentException("Unknown type "+ type);
		}
	}

    public IPowerup createPowerup(String type){
            if (type == null || type.isEmpty())
                return null;
            switch (type) {
            case "time":
                return new TimePowerup();
            case "hint":
                return new HintPowerup();
            case "vest":
                return new VestPowerup();
            case "bottle":
                return new BottlePowerup();
            case "life":
                return new LifePowerup();
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
            newPowerup = createPowerup(type);
			return newPowerup;
		}else {
			return null;
		}
    }

}
