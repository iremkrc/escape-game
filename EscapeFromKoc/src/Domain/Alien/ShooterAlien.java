package Domain.Alien;

import Domain.Controllers.GameController;
import Domain.Game.Location;

public class ShooterAlien implements Alien {

	private int width;
	private int height;
	private int size;
	private String type;
	private Location location;
	private Location avatarLocation;
	private GameController game;	
	private boolean empty;

    @Override
    public String getType() {
        return this.type;
    }
    
	public ShooterAlien(/*Location avatarLocation, boolean wornProtectionVest*/) {	
        type = "Shooter";
		empty = false;
		game = GameController.getInstance();
        width = game.getGridWidth();
        height = game.getGridHeight();
        size = game.getGridSize();
        location = game.getAvailableLocation(); 
        this.avatarLocation = game.getPlayer().getAvatar().getLocation(); //avatarLocation;
	}
    
    @Override
    public void action() {
    	double distancex = Math.abs(avatarLocation.getXLocation() - location.getXLocation());
    	double distancey = Math.abs(avatarLocation.getYLocation() - location.getYLocation());
    	
    	if(distancex < game.getGridSize()*4 && distancey < game.getGridSize()*4 && !game.getGameState().getIsVestPowerupActive()) {
    		game.getPlayer().getPlayerState().setHealth(game.getPlayer().getPlayerState().getHealth() - 1);
    		System.out.println(game.getPlayer().getPlayerState().getHealth());	
    	}    
    }

	@Override
	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
    
    public Location getLocation() {
		return location;
	}
    
    public int getSize() {
    	return size;
    }

	@Override
	public void setLocation(Location loc) {
		this.location = loc;
	}
}
