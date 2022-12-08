package Domain.GameObjects.Powerups;

public class VestPowerup extends Powerup {

    public VestPowerup(int unitLength) {
        super(unitLength);
        type = "vest";
        imagePath = "EscapeFromKoc/src/UI/Utilities/Images/vestPowerup.png";
    }
}
