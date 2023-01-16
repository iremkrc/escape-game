package Domain.Game;

import java.util.LinkedList;
import Domain.GameObjects.GameObject;

public class Building {
	public LinkedList<GameObject> gameObjectList = new LinkedList<GameObject>();
	private int currentObjectCount = 0;
	private int intendedObjectCount;
	private Door door;
	private boolean isFull;
	boolean doorOpen;
	public int width, height, gridSize;
	private int[][] gridNonAvailability;

	public String buildingName;
	
    public Building(String buildingName, int intendedObjectCount, int width, int height, int gridSize) {
    	this.buildingName = buildingName;
    	this.intendedObjectCount = intendedObjectCount;
		this.doorOpen = false; 
		this.isFull = false;
		this.height = height;
		this.width = width;
		this.gridSize = gridSize;
		gridNonAvailability = new int[width][height];
		door = new Door(gridSize, gridSize*width, gridSize*height);
		gridNonAvailability[width-1][height-1] = 1;
	}

	public void addAlien(int x, int y){
		return;
	}

	//add object to an empty location. Also check unreachable regions.
    public void addObject(int x ,int y) {
    	/*
    	   Requires: Being in the Building mode and clicking on the grid using the left mouse click
    	   			 Also the newly added object should not create unreachable location in the grid
    	   Modifies: currentObjectCount is incremented by 1
    	   			 gridNonAvailability array corresponding index becomes 1
    	   			 isFull becomes true if building becomes full of objects
    	   			 gameObjectList gets a new object
    	   Effects:  The building mode, objects and the GameController
    	*/			 
		int xGrid = (int) (x/gridSize);
		int yGrid = (int) (y/gridSize);
		if(xGrid == width && yGrid == height) {
			return;
		}
		
		System.out.println("x: " + xGrid + " y: " + yGrid);
		if(xGrid <=width && xGrid >=1 && yGrid <=height && yGrid >=1 && !(xGrid==1 && yGrid==1)) {
			if(!doesCauseUnreachableRegion(xGrid, yGrid)) {
				boolean isContained = false;
				int i = 0;
				for(GameObject obj: gameObjectList) {
					if(obj.getLocation().xGrid == xGrid && obj.getLocation().yGrid == yGrid) {
						isContained = true;
						break;
					}
					i++;
				}
				
				System.out.println("addObject");
				if(!isContained && !isFull) {
					GameObject obj = new GameObject(gridSize);
					obj.setLocation(gridSize*xGrid, gridSize*yGrid);
					gameObjectList.add(obj);
					gridNonAvailability[xGrid-1][yGrid-1] = 1;
			    	incrementCurrentObjectCount();
			    	if(currentObjectCount == intendedObjectCount) {
			    		isFull = true;
			    	}
				} 
				if (isContained) {
					isFull = false;
					gameObjectList.remove(i);
					gridNonAvailability[xGrid-1][yGrid-1] = 0;
					decrementCurrentObjectCount();
				}
			}
		}
    }
    
    // This method creates an empty array using the gridNonAvailability array.
    // Then checks if a new object addition in xGrid and yGrid causes
    // any unreachable area in the grid then returns a boolean.
    // Then the method also checks if there is any non-reachable object
    private boolean doesCauseUnreachableRegion(int xGrid, int yGrid) {
    	int [][] DFSCheckMatrix = new int[this.width][this.height];
		for(int i = 0; i < this.width; i++) {
			DFSCheckMatrix[i] = this.gridNonAvailability[i].clone();
		}
		DFSCheckMatrix[xGrid-1][yGrid-1] = 1;
		int[] loc = getInitialDFSLocation(DFSCheckMatrix);
		checkReachabilityDFS(DFSCheckMatrix, loc[0], loc[1]);
		if(!isArrayFull(DFSCheckMatrix)) {
			return true;
		}
		
		
    	int [][] DFSCheckMatrixObject = new int[this.width][this.height];
		for(int i = 0; i < this.width; i++) {
			DFSCheckMatrixObject[i] = DFSCheckMatrix[i].clone();
		}
		for(int i = 0; i < DFSCheckMatrixObject.length; i++) {
    		for(int j = 0; j < DFSCheckMatrixObject[i].length; j++) {
    			DFSCheckMatrixObject[i][j] = DFSCheckMatrixObject[i][j] - gridNonAvailability[i][j];
    		}
		}
		DFSCheckMatrixObject[xGrid-1][yGrid-1] = 0;
		
		
		int [][] DFSCheckMatrixObject2 = new int[this.width][this.height];
		for(int i = 0; i < this.width; i++) {
			DFSCheckMatrixObject2[i] = DFSCheckMatrixObject[i].clone();
		}
		
		for(int i = 0; i < DFSCheckMatrixObject2.length; i++) {
    		for(int j = 0; j < DFSCheckMatrixObject2[i].length; j++) {
    			if(DFSCheckMatrixObject[i][j] == 1) {
    				if(i-1>=0) DFSCheckMatrixObject2[i-1][j] = 1;
    				if(i+1<DFSCheckMatrixObject2.length) DFSCheckMatrixObject2[i+1][j] = 1;
    				if(j-1>=0) DFSCheckMatrixObject2[i][j-1] = 1;
    				if(j+1<DFSCheckMatrixObject2[0].length) DFSCheckMatrixObject2[i][j+1] = 1;
    				if(i+1<DFSCheckMatrixObject2.length && j+1<DFSCheckMatrixObject2[0].length) DFSCheckMatrixObject2[i+1][j+1] = 1;
    				if(i+1<DFSCheckMatrixObject2.length && j-1>=0) DFSCheckMatrixObject2[i+1][j-1] = 1;
    				if(i-1>=0 && j-1>=0) DFSCheckMatrixObject2[i-1][j-1] = 1;
    				if(i-1>=0 && j+1<DFSCheckMatrixObject2[0].length) DFSCheckMatrixObject2[i-1][j+1] = 1;
    			}
    		}
		}
		if(!isArrayFull(DFSCheckMatrixObject2)) {
			return true;
		}
		return false;
    }
	
    // This method starts from a location and traverses the whole arr that represents the map
    // Then the method makes all reachable indexes 1 and others left zero.
    // Then using that array, one can decide unreachability if there is zero value.
    private void checkReachabilityDFS(int[][] arr, int row, int col) {
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
    
    // checks if the array consists of all ones. (gridNonAvailability array is checked)
    private boolean isArrayFull(int[][] array) {
    	for(int i = 0; i < array.length; i++) {
    		for(int j = 0; j < array[i].length; j++) {
    			if(array[i][j] == 0) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    // This method returns the location of an empty index to start DFS.
    private int[] getInitialDFSLocation(int[][] array) {
    	int[] location = new int[2];
    	for(int i = 0; i < array.length; i++) {
    		for(int j = 0; j < array[i].length; j++) {
    			if(array[i][j] == 0) {
    				location[0] = i;
    				location[1] = j;

    				return location;
    			}
    		}
    	}
		location[0] = -1;
		location[1] = -1;
		
    	return location;
    }
    
    public boolean getGridAvailability(int x, int y) {
    	return this.gridNonAvailability[x-1][y-1] == 0;
    }
    
    public void setGridAvaliabilityMatrix(int[][] matrix){
    	this.gridNonAvailability = matrix;
    }
    
    public boolean getIsFull() {
    	return isFull;
    }
    public void setIsFull(Boolean isFull) {
    	this.isFull = isFull;
    }
    public int getCurrentObjectCount() {
		return currentObjectCount;
	}
    
    public void setCurrentObjectCount(int count) {
		this.currentObjectCount =  count;
	}
    
    public void incrementCurrentObjectCount() {
 		this.currentObjectCount++;
 	}
    
    public void decrementCurrentObjectCount() {
 		this.currentObjectCount--;
 	}
    
    public int getIntendedObjectCount() {
		return intendedObjectCount;
	}
    
    public void setIntendedObjectCount(int intendedObjectCount) {
		this.intendedObjectCount = intendedObjectCount;
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
	
	public Location getDoorLocation() {
		return door.getLocation();
	}
	
	public void setDoorState(boolean flag) {
		door.setIsOpen(flag);
	}
	
	public void setDoor(Door door) {
		this.door = door;
	}
	public Door getDoor() {
		return door;
	}


	public void setGameObjectList(LinkedList<GameObject> objList) {
		// TODO Auto-generated method stub
		this.gameObjectList = objList;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getGridSize() {
		return gridSize;
	}


	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}


	public int[][] getGridNonAvailability() {
		return gridNonAvailability;
	}


	public void setGridNonAvailability(int[][] gridNonAvailability) {
		this.gridNonAvailability = gridNonAvailability;
	}


	public LinkedList<GameObject> getGameObjectList() {
		return gameObjectList;
	}


	public boolean isDoorOpen() {
		return doorOpen;
	}


	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
	
	
}