package Domain.GameObjects.Powerups;

public class TimePowerup extends Powerup {

    public TimePowerup(int unitLength) {
        super(unitLength);
        type = "time";
        imagePath = "EscapeFromKoc/src/UI/Utilities/Images/timePowerup.png";
    } 
}
