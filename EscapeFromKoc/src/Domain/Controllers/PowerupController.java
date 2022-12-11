package Domain.Controllers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import Domain.GameObjects.Powerups.PowerupFactory;
import Domain.GameObjects.Powerups.IPowerup;

public class PowerupController {
    PowerupFactory factory;
    IPowerup powerup;

    public PowerupController() {
        factory = new PowerupFactory();
    }

    public IPowerup getPowerup(){
        return powerup;
    }

    public void setPowerup(IPowerup powerup){
        this.powerup = powerup;
    }

    public IPowerup createPowerupRandomly() {
        List<String> powerupTypeList = Arrays.asList("time", "hint", "vest", "bottle", "life");
        int randomTypeIndex = ThreadLocalRandom.current().nextInt(powerupTypeList.size()) % powerupTypeList.size();
        String randomType = powerupTypeList.get(randomTypeIndex);
        IPowerup powerup = factory.createPowerup(randomType);
        System.out.println(randomType);
        return powerup;
    }
}
