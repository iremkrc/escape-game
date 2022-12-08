package Domain.GameObjects.Powerups;

public class LifePowerup extends Powerup {

    public LifePowerup(int unitLength) {
        super(unitLength);
        type = "life";
        imagePath = "EscapeFromKoc/src/UI/Utilities/Images/lifePowerup.png";
    }   
}
