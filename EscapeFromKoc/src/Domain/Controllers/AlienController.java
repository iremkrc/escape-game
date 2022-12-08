package Domain.Controllers;

import Domain.Alien.Alien;
import Domain.Alien.AlienFactory;

public class AlienController {
    AlienFactory factory = new AlienFactory();
    Alien alien = factory.createAlien("Blind");
    String type = alien.getType();
}
