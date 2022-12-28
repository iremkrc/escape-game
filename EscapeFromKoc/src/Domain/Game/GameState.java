package Domain.Game;

public class GameState {
	private final int buildingCount = 6;
	public String[] buildingNames = {"Student Center","CASE","SOS","SCI","ENG","SNA"}; //
	public int[] objCounts = {1,1,1,1,1,1};  //5,7,10,14,19,25
	private int timeGivenForEachObject = 600; // the given time for the building is this times the object count //10
	private boolean paused = false;
	private boolean isOver = false;
	private boolean hintActive = false;
	private boolean isBottlePowerupActive = false;
	private boolean isKeyFound = false;
	private int currentBuildingIndex = 0;
	private int time;
	
	public GameState(){
		this.time = 0;
	}

	public boolean isPaused() {
		return paused;
	}
	
	public void setPaused(boolean b){
		this.paused = b;
	}

	public int getTotalTime(){
		return objCounts[currentBuildingIndex]*timeGivenForEachObject;
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean getHintActive() {
		return hintActive;
	}
	
	public void setHintActive(boolean hintActive) {
		this.hintActive = hintActive;
	}

	public boolean getIsBottlePowerupActive() {
		return isBottlePowerupActive;
	}

	public void setIsBottlePowerupActive(boolean isBottlePowerupActive) {
		this.isBottlePowerupActive = isBottlePowerupActive;
	}

	public void setNewBuildingTime() {
		this.setTime(objCounts[currentBuildingIndex]*timeGivenForEachObject);
	}

	public boolean isKeyFound() {
		return isKeyFound;
	}

	public void setKeyFound(boolean b) {
		this.isKeyFound = b;
	}

	public int getCurrentBuildingIndex() {
		return currentBuildingIndex;
	}
	
	public int getBuildingCount() {
		return buildingCount;
	}
	
	public void setCurrentBuildingIndex(int index) {
		this.currentBuildingIndex = index;
	}
	
	public void setIsOver(boolean b) {
		this.isOver = b;
	}
	
	public boolean isOver() {
		return this.isOver;
	}
}