package Domain.Game;

public class EscapeFromKocGame{

    Player player;
    private static EscapeFromKocGame instance;
	boolean isPaused = false;
	boolean isOver = false;
	public int timeLeft;

    public static EscapeFromKocGame getInstance() {
		if (instance == null)
			instance = new EscapeFromKocGame();
		return instance;
	}

    public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

    public boolean isPaused() {
        return isPaused;
    }

    public void isGameOver() {
		boolean isDead = player.getHealth() <= 0;
		boolean noTime = timeLeft <= 0;
		isOver = isDead||noTime;
		if(isOver) {
			double collectionTime = (600000-timeLeft) / 1000;
			player.incrementScore(1 / collectionTime);
		}
	}
   
    public void setPlayer(Player player) {
		this.player=player;
	}

    public Player getPlayer() {
		return player;
	}

    public boolean isOver() {
		return isOver;
	}

    public int getTimeLeft() {
        return timeLeft;
	}

    public void moveAvatar(String direction) {
		player.moveAvatar(direction);
	}

	public void pickKey() {
		player.pickKey();
	}

	public void catchPowerup(String type) {
		player.catchPowerup();
	}

	public void incrementScore(double increment) {
		player.incrementScore(increment);
	}



















}



