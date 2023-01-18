package Domain.Alien;

import Domain.Controllers.GameController;
import Domain.Game.Location;


public class TimeWastingAlien implements Alien {

    private int width;
	private int height;
	private int size;
	private String type;
	private Location location;
    private TimeWastingStrategy strategy;
    private boolean empty;
    private GameController game;

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Location getLocation() {
        return this.location;
    }

	@Override
	public void setLocation(Location loc) {
		this.location = loc;
	}

    public TimeWastingStrategy getStrategy() {
        return this.strategy;
    }

    public TimeWastingStrategy findStrategy(int totalTime, int remainingTime) {
        // Requires: Game is in the running mode.
        // Modifies: TimeWastingStrategy of the TimeWastingAlien.
        // Effects: If strategy has decided, sets the strategy of TimeWastingAlien.
        TimeWastingStrategy strategy;
        if(remainingTime < totalTime * 0.3) {
            strategy = new LimitedStrategy();
        }else if(remainingTime > totalTime * 0.7) {
            strategy = new ChallengingStrategy();
        }else{
            strategy = new ConfusedStrategy();
        }
        return strategy;
    }

    public void setStrategy(TimeWastingStrategy strategy) {
    	strategy.setAlien(this);
        this.strategy = strategy;
    }
    

    public TimeWastingAlien() {
        type = "TimeWasting";
        empty = false;
        game = GameController.getInstance();
        width = game.getGridWidth();
        height = game.getGridHeight();
        size = game.getGridSize();
        location = game.getAvailableLocation();
    }
    
    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return this.type;
    }

    @Override
    public void action() {
        // TODO Auto-generated method stub
    	if(!game.getGameState().isKeyFound()) {
            strategy.wasteTime();
    	}
    }
    
    @Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	
    public int getSize() {
    	return size;
    }
}
