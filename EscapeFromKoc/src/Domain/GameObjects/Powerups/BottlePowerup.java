package Domain.GameObjects.Powerups;

public class BottlePowerup extends Powerup {

    public BottlePowerup(int unitLength) {
        super(unitLength);
        type = "bottle";
        imagePath = "EscapeFromKoc/src/UI/Utilities/Images/bottlePowerup.png";
    }
}
