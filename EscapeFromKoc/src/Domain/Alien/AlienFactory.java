package Domain.Alien;

public class AlienFactory {
    public Alien createAlien(String type) {
        if (type.equals("Blind")) {
            return new BlindAlien();
        } else if (type.equals("Shooter")) {
            return new ShooterAlien();
        } else if (type.equals("TimeWasting")) {
            return new TimeWastingAlien();
        } else {
            throw new IllegalArgumentException("Unknown alien type: " + type);
        }
    }
}
