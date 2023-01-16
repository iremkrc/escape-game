package Domain.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GameState {
	private final int buildingCount = 6;
	private boolean buildingModeDone = false;
	public String[] buildingNames = {"Student Center","CASE","SOS","SCI","ENG","SNA"}; //
	public int[] objCounts = {1,1,1,1,1,1};  //5,7,10,14,19,25
	public static final int height = 12;
	public static final int width = 18;
	public static final int gridSize = 40;
	public int timeGivenForEachObject = 100; // the given time for the building is this times the object count //10
	private boolean paused = false;
	private boolean isOver = false;
	private boolean hintActive = false;
	private boolean isBottlePowerupActive = false;
	private boolean isVestPowerupActive = false;
	private boolean isKeyFound = false;
	private boolean Won = false;
	private int currentBuildingIndex = 0;
	private int time;
	
	ActionListener timeListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!paused){
				System.out.println("time: "+time);
				int second = time - 1;
				setTime(second);
				if(getTime()==0){
					setIsOver(true);
				}
			}
		}
	};
	
	Timer healthTimer = new Timer(1000, timeListener);
	
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
	
	public boolean getIsVestPowerupActive() {
		return isVestPowerupActive;
	}

	public void setIsBottlePowerupActive(boolean isBottlePowerupActive) {
		this.isBottlePowerupActive = isBottlePowerupActive;
	}
	
	public void setIsVestPowerupActive(boolean isVestPowerupActive) {
		this.isVestPowerupActive = isVestPowerupActive;
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
	
	public void startGameTimer() {
		System.out.println("game timer start");
		healthTimer.start();
	}
	
	public boolean isWon() {
		return Won;
	}

	public void setWon(boolean won) {
		Won = won;
	}
		
}