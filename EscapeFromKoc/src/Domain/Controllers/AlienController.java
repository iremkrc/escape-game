package Domain.Controllers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import Domain.Alien.Alien;
import Domain.Alien.AlienFactory;

public class AlienController {

    AlienFactory factory;

    public AlienController() {
        factory = new AlienFactory();
    }

    public Alien createAlienRandomly() {
        List<String> alienTypeList = Arrays.asList("Blind", "Shooter", "TimeWasting");
        int randomTypeIndex = ThreadLocalRandom.current().nextInt(alienTypeList.size()) % alienTypeList.size();
        String randomType = alienTypeList.get(randomTypeIndex);
        Alien alien = factory.createAlien(randomType);
        return alien;
    }
}
