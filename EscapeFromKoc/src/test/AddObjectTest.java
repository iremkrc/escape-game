package test;


import Domain.Controllers.GameController;
import Domain.Controllers.PlayerController;
import Domain.Game.Building;
import Domain.Game.PlayerState;
import Domain.GameObjects.GameObject;
import Domain.Player.Inventory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class AddObjectTest {
	// This class implements tests to check the "addObject" method inside the Building class
	// But that method is called from the GameController using "addObjectToCurrentBuilding"
	static PlayerController player;	
	static PlayerState playerState;	
	static Inventory inventory; 

    @Test
    public void testOneAddition() {
    	// Black Box Test
        // Requires: Game passed to running mode
        // Modifies: Objects in a specific building
        // Effects: makes number of objects zero in the building.

        GameController game = new GameController();
        Building currBuild = game.getCurrentBuilding();
        System.out.println(game.getCurrentBuildingIndex());
        System.out.println(game.currentBuilding.getCurrentObjectCount());
        assertEquals(currBuild.getObjectList().size(),0); // get number of objects in a building

        game.addObjectToCurrentBuilding(500,500); //add one object
        assertEquals(currBuild.getObjectList().size(),1); // get number of objects in a building
        
        game.addObjectToCurrentBuilding(700,700); //add one object to the same place
        assertEquals(currBuild.getObjectList().size(),1);
    }
    
    @Test
    public void testTwoAdditionOnTop() {
    	// Black Box Test
        // Requires: Game passed to running mode
        // Modifies: Objects in a specific building
        // Effects: makes number of objects zero in the building.

        GameController game = new GameController();
        Building currBuild = game.getCurrentBuilding();
        System.out.println(game.currentBuilding.getCurrentObjectCount());
        assertEquals(currBuild.getObjectList().size(),0); // get number of objects in a building

        System.out.println(game.currentBuilding.getCurrentObjectCount());
        game.addObjectToCurrentBuilding(500,500); //add one object
        assertEquals(currBuild.getObjectList().size(),1); // get number of objects in a building

        game.addObjectToCurrentBuilding(500,500); //add object to the same place
        assertEquals(currBuild.getObjectList().size(),1);
    }
    
    @Test
    public void testOutOfMapAddition() {
    	// Black Box Test
        // Requires: Game passed to running mode
        // Modifies: Objects in a specific building
        // Effects: makes number of objects zero in the building.

        GameController game = new GameController();
        Building currBuild = game.getCurrentBuilding();
        System.out.println(game.getCurrentBuildingIndex());
        System.out.println(game.currentBuilding.getCurrentObjectCount());        
        assertEquals(currBuild.getObjectList().size(),0);

        game.addObjectToCurrentBuilding(700,700); 
        assertEquals(currBuild.getObjectList().size(),0);
        
        game.addObjectToCurrentBuilding(0,500);
        assertEquals(currBuild.getObjectList().size(),0);
        
        game.addObjectToCurrentBuilding(0,0);
        assertEquals(currBuild.getObjectList().size(),0);
        
        game.addObjectToCurrentBuilding(-175,500);
        assertEquals(currBuild.getObjectList().size(),0);
        
        game.addObjectToCurrentBuilding(1500,1000); 
        assertEquals(currBuild.getObjectList().size(),0);
        
        game.addObjectToCurrentBuilding(1000,200);
        assertEquals(currBuild.getObjectList().size(),0);
        
        game.addObjectToCurrentBuilding(300,0);
        assertEquals(currBuild.getObjectList().size(),0);        
    }
    
    @Test
    public void testMultipleAdditions() {
    	// Glass Box Test
        // Requires: Game passed to running mode
        // Modifies: Objects in a specific building
        // Effects: makes number of objects zero in the building.
    	GameController game = new GameController();
        Building currBuild = game.getCurrentBuilding();
        int ct = 0;
        for(int i = 50; i<=500; i+=50) {
        	for(int j = 50; j<=500; j+=50) {
        		
        		System.out.println(ct);
        		System.out.println(currBuild.getObjectList().size());
        		if(ct < currBuild.getIntendedObjectCount()) {
        			game.addObjectToCurrentBuilding(i,j); //add one object
        			ct++;
        			assertEquals(currBuild.getObjectList().size(),ct);
        		}                
            }
        }
        game.addObjectToCurrentBuilding(500,500); //add one object
        assertEquals(currBuild.getObjectList().size(), currBuild.getIntendedObjectCount());
    }
    
    @Test
    public void testUnreachableAreas() {
    	// Black Box Test
        // Requires: Game passed to running mode
        // Modifies: Objects in a specific building
        // Effects: makes number of objects zero in the building.

    	GameController game = new GameController();
        Building currBuild = game.getCurrentBuilding();
        
        game.addObjectToCurrentBuilding(200,200); //add one object
        assertEquals(currBuild.getObjectList().size(),1);
        
        game.addObjectToCurrentBuilding(150,250); //add one object
        assertEquals(currBuild.getObjectList().size(),2);
        
        game.addObjectToCurrentBuilding(200,300); //add one object
        assertEquals(currBuild.getObjectList().size(),3);
        
        game.addObjectToCurrentBuilding(250,250); //add one object that cause unreachable place
        assertEquals(currBuild.getObjectList().size(),3);
        
        
        
        game.addObjectToCurrentBuilding(50,100); //add one object
        assertEquals(currBuild.getObjectList().size(),4);
        
        game.addObjectToCurrentBuilding(100,100); //add one object
        assertEquals(currBuild.getObjectList().size(),5);
        
        game.addObjectToCurrentBuilding(100,50); //add one object
        assertEquals(currBuild.getObjectList().size(),5);
        
    }

}