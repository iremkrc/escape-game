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
	int[][] gridNonAvailability = new int[width][height];

	public String buildingName;
	
    public Building(String buildingName, int intendedObjectCount) {
    	this.buildingName = buildingName;
    	this.intendedObjectCount = intendedObjectCount;
		this.doorOpen = false; 
		this.isFull = false;
	}

	public void addAlien(int x, int y){
		
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
    
    public boolean doesCauseUnreachableRegion(int xGrid, int yGrid) {
    	int [][] DFSCheckMatrix = new int[this.width][this.height];
		for(int i = 0; i < this.width; i++) {
			DFSCheckMatrix[i] = this.gridNonAvailability[i].clone();
		}
		
		DFSCheckMatrix[xGrid][yGrid] = 1;
		
		int[] loc = getInitialDFSLocation(DFSCheckMatrix);
		checkReachabilityDFS(DFSCheckMatrix, loc[0], loc[1]);
		for(int i = 0; i < DFSCheckMatrix.length; i++) {
    		for(int j = 0; j < DFSCheckMatrix[i].length; j++) {
    			System.out.println(DFSCheckMatrix[i][j]);
    		}
    	}
		
		if(!isArrayFull(DFSCheckMatrix)) {
			return true;
		}
		return false;
    }
    
    public void checkReachabilityDFS(int[][] arr, int row, int col) {
		if (row < 0 || col < 0) {
			return;
		}
		if(row >= arr.length || col >= arr[0].length) {
			return;
		}
		if (arr[row][col] == 1) {
			return;
		}
		if(arr[row][col] == 0) {
			arr[row][col] = 1;
		}
		
		for(int r = row-1; r<=row+1; r+=2) {
			checkReachabilityDFS(arr,r,col);
		}
		for(int c = col-1; c<=col+1; c+=2) {
			checkReachabilityDFS(arr,row,c);
		}
	}
    
    public static boolean isArrayFull(int[][] array) {
    	for(int i = 0; i < array.length; i++) {
    		for(int j = 0; j < array[i].length; j++) {
    			if(array[i][j] == 0) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public int[] getInitialDFSLocation(int[][] array) {
    	int[] location = new int[2];
    	for(int i = 0; i < array.length; i++) {
    		for(int j = 0; j < array[i].length; j++) {
    			if(array[i][j] == 0) {
    				location[0] = i;
    				location[0] = j;

    				return location;
    			}
    		}
    	}
		location[0] = -1;
		location[0] = -1;
		
    	return location;
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