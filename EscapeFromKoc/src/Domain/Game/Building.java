package Domain.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import Domain.GameObjects.GameObject;

public class Building {
	public LinkedList<GameObject> gameObjectList = new LinkedList<GameObject>();
	private int currentObjectCount = 0;
	private int intendedObjectCount;
	private boolean isFull;
	boolean doorOpen;
	public int width = 10;
	public int height = 10;

	public String buildingName;
	
    public Building(String buildingName, int intendedObjectCount) {
    	this.buildingName = buildingName;
    	this.intendedObjectCount = intendedObjectCount;
		this.doorOpen = false; 
		this.isFull = false;
	}

    public void addObject(int x ,int y) {
		int xGrid = (int) (x/50);
		int yGrid = (int) (y/50);
		
		System.out.println("x: " + xGrid + " y: " + yGrid);
		if(xGrid <=10 && xGrid >=1 && yGrid <=10 && yGrid >=1) {
			boolean isContained = false;
			for(GameObject obj: gameObjectList) {
				if(obj.getLocation().xGrid == xGrid && obj.getLocation().yGrid == yGrid) {
					isContained = true;
					break;
				}
			}
			if(!isContained) {
				GameObject obj = new GameObject(25);
				obj.setLocation(50*xGrid+20, 50*yGrid+10);
				gameObjectList.add(obj);
		    	incrementCurrentObjectCount();
		    	
		    	if(currentObjectCount == intendedObjectCount) {
		    		isFull = true;
		    	}
			}
		}
    }
    
    public boolean getIsFull() {
    	return isFull;
    }
    
    public int getCurrentObjectCount() {
		return currentObjectCount;
	}
    
    public void incrementCurrentObjectCount() {
 		this.currentObjectCount++;
 	}
    
    public void decrementCurrentObjectCount() {
 		this.currentObjectCount++;
 	}
    
    public int getIntendedObjectCount() {
		return intendedObjectCount;
	}
    
	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public LinkedList<GameObject> getObjectList() {
		return gameObjectList;
	}
    
    

}