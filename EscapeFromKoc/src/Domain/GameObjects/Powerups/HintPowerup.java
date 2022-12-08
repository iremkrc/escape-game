package Domain.GameObjects.Powerups;

public class HintPowerup extends Powerup {

    public HintPowerup(int unitLength) {
        super(unitLength);
        type = "hint";
        imagePath = "EscapeFromKoc/src/UI/Utilities/Images/hintPowerup.png";
    }   
}
