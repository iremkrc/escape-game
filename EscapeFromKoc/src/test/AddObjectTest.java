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
	static GameController game;
	static Building currBuild;

	@BeforeEach
    public void everyTime(){
        game = new GameController();
        currBuild = game.getCurrentBuilding();
        game.getGameState().objCounts[0] = 20;
    }
	
    @Test
    public void testOneAddition() {
    	// Black Box Test
    	// This test tries adding an object to a location
    	// The object count should be 1 after the addition
    	
        assertEquals(currBuild.getObjectList().size(),0); // BB Test initially zero objects

        game.addObjectToCurrentBuilding(500,500); //BB Test add one object
        assertEquals(currBuild.getObjectList().size(),1); // get number of objects in a building
        
        game.addObjectToCurrentBuilding(700,700); //BB Test add one object to the outside of the grid
        assertEquals(currBuild.getObjectList().size(),1); // object count should still be 1
    }
    
    @Test
    public void testTwoAdditionOnTop() {
    	// Black Box Test
    	// This test tries adding objects to the same location
    	// The function should not add the second object in these cases
    	// The second add object will remove the previously put object

        assertEquals(currBuild.getObjectList().size(),0); // get number of objects in a building

        game.addObjectToCurrentBuilding(400,400); //BB Test add one object
        assertEquals(currBuild.getObjectList().size(),1); // get number of objects in a building

        game.addObjectToCurrentBuilding(400,400); //BB Test add object to the same place
        assertEquals(currBuild.getObjectList().size(),0);
    }
    
    @Test
    public void testOutOfMapAddition() {
    	// Black Box Test
    	// This test tries adding objects to out of the grid
    	// The function should not add objects in these cases
    	
        assertEquals(currBuild.getObjectList().size(),0);

        game.addObjectToCurrentBuilding(700,700); //BB Test add one object to the outside of the grid
        assertEquals(currBuild.getObjectList().size(),0);
        
        game.addObjectToCurrentBuilding(0,500); //BB Test add one object to the outside of the grid
        assertEquals(currBuild.getObjectList().size(),0);
        
        game.addObjectToCurrentBuilding(0,0); //BB Test add one object to the outside of the grid
        assertEquals(currBuild.getObjectList().size(),0);
        
        game.addObjectToCurrentBuilding(-175,500); //BB Test add one object to the outside of the grid
        assertEquals(currBuild.getObjectList().size(),0);
        
        game.addObjectToCurrentBuilding(1500,1000); //BB Test add one object to the outside of the grid
        assertEquals(currBuild.getObjectList().size(),0);
        
        game.addObjectToCurrentBuilding(1000,200); //BB Test add one object to the outside of the grid
        assertEquals(currBuild.getObjectList().size(),0);
        
        game.addObjectToCurrentBuilding(300,0); //BB Test add one object to the outside of the grid
        assertEquals(currBuild.getObjectList().size(),0);        
    }
    
    @Test
    public void testMultipleAdditions() {
    	// Glass Box Test
    	// This method tried to add more objects than the maximum amount
    	
        int ct = 0;
        for(int i = 40; i<=400; i+=40) {
        	for(int j = 80; j<=400; j+=40) {
        		
        		System.out.println(ct);
        		System.out.println(currBuild.getObjectList().size());
        		if(ct < currBuild.getIntendedObjectCount()) {
        			game.addObjectToCurrentBuilding(i,j); //add one object
        			ct++;
        			assertEquals(currBuild.getObjectList().size(),ct); //BB add one object
        		}                
            }
        }
        game.addObjectToCurrentBuilding(500,500); // GB Test building is full and the if condition is not satisfied
        assertEquals(currBuild.getObjectList().size(), currBuild.getIntendedObjectCount());
    }
    
    @Test
    public void testUnreachableAreas() {
    	// Black Box Test
        // This method checks if the addObject function denies 
    	// adding object that creates unreachable grid loation

        game.addObjectToCurrentBuilding(160,160); //add one object
        assertEquals(currBuild.getObjectList().size(),1);
        
        game.addObjectToCurrentBuilding(120,200); //add one object
        assertEquals(currBuild.getObjectList().size(),2);
        
        game.addObjectToCurrentBuilding(160,240); //add one object
        assertEquals(currBuild.getObjectList().size(),3);
        
        game.addObjectToCurrentBuilding(200,200); //add one object that cause unreachable place
        assertEquals(currBuild.getObjectList().size(),3); // BB Test add object that creates unreachable area
        
        // Reset building
        game = new GameController();
        currBuild = game.getCurrentBuilding();
        game.getGameState().objCounts[0] = 20;
        
        game.addObjectToCurrentBuilding(320,320); //add one object
        assertEquals(currBuild.getObjectList().size(),1);
        
        game.addObjectToCurrentBuilding(280,360); //add one object
        assertEquals(currBuild.getObjectList().size(),2);
        
        game.addObjectToCurrentBuilding(320,400); //add one object
        assertEquals(currBuild.getObjectList().size(),3);
        
        game.addObjectToCurrentBuilding(360,360); //add one object that cause unreachable place
        assertEquals(currBuild.getObjectList().size(),3); // BB Test add object that creates unreachable area
        
        // Reset building
        game = new GameController();
        currBuild = game.getCurrentBuilding();
        game.getGameState().objCounts[0] = 20;
        
        game.addObjectToCurrentBuilding(40,80); //add one object
        assertEquals(currBuild.getObjectList().size(),1);
        
        game.addObjectToCurrentBuilding(80,80); //add one object
        assertEquals(currBuild.getObjectList().size(),2);
        
        game.addObjectToCurrentBuilding(80,40); //add one object
        assertEquals(currBuild.getObjectList().size(),2); // BB Test add object that creates unreachable area
        
    }

}