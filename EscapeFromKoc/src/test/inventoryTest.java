package test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Domain.Controllers.GameController;
import Domain.Controllers.PlayerController;
import Domain.Game.PlayerState;
import Domain.Player.Inventory;

public class inventoryTest {

	static PlayerController player;	
	static PlayerState playerState;	
	static Inventory inventory; 
	static GameController game;
	static HashMap<String, Integer> powerupMap;

	@AfterAll
	static void tearDownAfterClass() throws Exception {}

	@BeforeEach
	void setUp() throws Exception {
		//setting initial number of power-ups to random values 
		player = new PlayerController();
		playerState = new PlayerState();
		inventory = playerState.inventory;
		game = new GameController();
		powerupMap = inventory.getPowerupsMap();
		
		powerupMap.put("hint", 3); // make power-up counts 3
		powerupMap.put("vest", 3); // make power-up counts 3
		powerupMap.put("bottle", 3); // make power-up counts 3
	}

	@AfterEach
	void tearDown() throws Exception {}
	
	@Test
	public void checkInventoryCheck() {
		/*
		 * testing whether checkInventory works for power-ups 
		 */
		String powerupType = "vest";//or any other powerupType 
		boolean found = inventory.checkInventory(powerupType);
		assertEquals(true, inventory.repOk()); // check repOk
		assertEquals(found,inventory.getPowerupsMap().get(powerupType) != 0); // BB Test
		
		String powerupType1 = "bottle";//or any other powerupType 
		boolean found1 = inventory.checkInventory(powerupType1);
		assertEquals(true, inventory.repOk()); // check repOk
		assertEquals(found1,inventory.getPowerupsMap().get(powerupType1) != 0); // BB Test
		
		String powerupType2 = "hint";//or any other powerupType 
		boolean found2 = inventory.checkInventory(powerupType2);
		assertEquals(true, inventory.repOk()); // check repOk
		assertEquals(found2,inventory.getPowerupsMap().get(powerupType2) != 0); // BB Test
		
		//testing for 0 amount of power-ups 
		inventory.getPowerupsMap().put(powerupType, 0);
		assertEquals(true, inventory.repOk()); // check repOk
		found = inventory.checkInventory(powerupType);
		assertEquals(true, inventory.repOk()); // check repOk
		assertEquals(found,inventory.getPowerupsMap().get(powerupType) != 0); // BB Test
	}
	
	@Test
	public void decrementPowerupCheck() throws Exception {
		/*
		 * This method checks the decrementPowerup method which decrements the power-up count
		 */
		String powerupType1 = "vest"; // for vest power-up
		inventory.getPowerupsMap().put(powerupType1, 1);
		assertEquals(true, inventory.repOk()); // check repOk
		inventory.decrementPowerups(powerupType1); // decrement 1
		assertEquals(true, inventory.repOk()); // check repOk
		assertEquals(true,inventory.getPowerupsMap().get(powerupType1) == 0); // BB Test if its decremented
		
		String powerupType2 = "bottle"; // for bottle power-up
		inventory.getPowerupsMap().put(powerupType2, 1);
		assertEquals(true, inventory.repOk()); // check repOk
		inventory.decrementPowerups(powerupType2); // decrement 1
		assertEquals(true, inventory.repOk()); // check repOk
		assertEquals(true,inventory.getPowerupsMap().get(powerupType2) == 0); // BB Test if its decremented
		
		String powerupType3 = "hint"; // for hint power-up
		inventory.getPowerupsMap().put(powerupType3, 1);
		assertEquals(true, inventory.repOk()); // check repOk
		inventory.decrementPowerups(powerupType3); // decrement 1
		assertEquals(true, inventory.repOk()); // check repOk
		assertEquals(true,inventory.getPowerupsMap().get(powerupType3) == 0); // BB Test if its decremented
	}
	
	@Test
	public void incrementPowerupCheck() throws Exception {
		/*
		 * This method checks the incrementPowerup method which increments the power-up count
		 */
		String powerupType1 = "vest"; // for vest power-up
		inventory.getPowerupsMap().put(powerupType1, 0);
		assertEquals(true, inventory.repOk()); // check repOk
		inventory.incrementPowerups(powerupType1); // increment 1
		assertEquals(true, inventory.repOk()); // check repOk
		assertEquals(true, inventory.getPowerupsMap().get(powerupType1) == 1); // BB Test increase 0 to 1
		
		String powerupType2 = "bottle"; // for bottle power-up
		inventory.getPowerupsMap().put(powerupType2, 1);
		assertEquals(true, inventory.repOk()); // check repOk
		inventory.incrementPowerups(powerupType2); // increment 1
		assertEquals(true, inventory.repOk()); // check repOk
		assertEquals(true, inventory.getPowerupsMap().get(powerupType2) == 2); // BB Test increase 1 to 2
		
		String powerupType3 = "hint"; // for hint power-up 
		inventory.getPowerupsMap().put(powerupType3, 2);
		assertEquals(true, inventory.repOk()); // check repOk
		inventory.incrementPowerups(powerupType3); // increment 1
		assertEquals(true, inventory.repOk()); // check repOk
		assertEquals(true, inventory.getPowerupsMap().get(powerupType3) == 3); // BB Test increase 2 to 3
	}
	
	@Test
	public void getPowerupCheck() throws Exception {
		/*
		 * This method checks the getPowerup method which returns the power-up name or null if doesn't exist
		 */
		powerupMap.put("hint", 1);
		assertEquals(true, inventory.repOk()); // check repOk
		powerupMap.put("vest", 2);
		assertEquals(true, inventory.repOk()); // check repOk
		powerupMap.put("bottle", 3);
		assertEquals(true, inventory.repOk()); // check repOk

		assertEquals("hint", inventory.getPowerup("hint")); // GB Test that enter the if condition
		assertEquals(true, inventory.repOk()); // check repOk
		assertEquals("vest", inventory.getPowerup("vest")); // GB Test that enter the if condition
		assertEquals(true, inventory.repOk()); // check repOk
		assertEquals("bottle", inventory.getPowerup("bottle")); // GB Test that enter the if condition
		assertEquals(true, inventory.repOk()); // check repOk
		assertEquals(null, inventory.getPowerup("health")); // GB Test that doesn't enter the if condition
		assertEquals(true, inventory.repOk()); // check repOk
	}
	
	@Test
	public void getPowerupCountCheck() throws Exception {
		/*
		 * This method checks the getPowerupCount method which returns the power-up count in the inventory
		 */
		powerupMap.put("hint", 1);
		assertEquals(true, inventory.repOk()); // check repOk
		powerupMap.put("vest", 2);
		assertEquals(true, inventory.repOk()); // check repOk
		powerupMap.put("bottle", 3);
		assertEquals(true, inventory.repOk()); // check repOk

		assertEquals(1, inventory.getPowerupCount("hint")); // BB Test get count
		assertEquals(true, inventory.repOk()); // check repOk
		assertEquals(2, inventory.getPowerupCount("vest")); // BB Test get count
		assertEquals(true, inventory.repOk()); // check repOk
		assertEquals(3, inventory.getPowerupCount("bottle")); // BB Test get count
		assertEquals(true, inventory.repOk()); // check repOk
	}
	
	@Test
	public void repOkCheck() throws Exception {
		assertEquals(true, inventory.repOk()); // check repOk
	}


}
