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
		
		powerupMap.put("hint", 3);
		powerupMap.put("vest", 3);
		powerupMap.put("bottle", 3);
	}

	@AfterEach
	void tearDown() throws Exception {}
	
	@Test
	public void powerupCountCheck() {
		System.out.println("yess");
		//testing whether checkInventory works for power-ups 
		String powerupType = "vest";//or any other powerupType 
		boolean found = inventory.checkInventory(powerupType);
		
		assertEquals(found,inventory.getPowerupsMap().get(powerupType) != 0);
		
		//testing for 0 amount of power-ups 
		inventory.getPowerupsMap().put(powerupType, 0);
		found = inventory.checkInventory(powerupType);
		assertEquals(found,inventory.getPowerupsMap().get(powerupType) != 0);
	}
	
	@Test
	public void decrementPowerupCheck() throws Exception {
		String powerupType = "vest"; //or any other powerupType 
		inventory.decrementPowerups(powerupType);
		boolean isFound = inventory.checkInventory(powerupType);
		assertEquals(isFound,inventory.getPowerupsMap().get(powerupType) != 0);

	}
	
	@Test
	public void incrementPowerupCheck() throws Exception {
		String powerupType = "vest"; //or any other powerupType 
		inventory.getPowerupsMap().put(powerupType, 0);
		inventory.incrementPowerups(powerupType);
		assertEquals(true, inventory.getPowerupsMap().get(powerupType) == 1);

	}
	
	@Test
	public void getPowerupCheck() throws Exception {
		powerupMap.put("hint", 1);
		powerupMap.put("vest", 2);
		powerupMap.put("bottle", 3);

		assertEquals("hint", inventory.getPowerup("hint"));
		assertEquals("vest", inventory.getPowerup("vest"));
		assertEquals("bottle", inventory.getPowerup("bottle"));
		assertEquals(null, inventory.getPowerup("health"));
	}
	
	@Test
	public void getPowerupCountCheck() throws Exception {
		powerupMap.put("hint", 1);
		powerupMap.put("vest", 2);
		powerupMap.put("bottle", 3);

		assertEquals(1, inventory.getPowerupCount("hint"));
		assertEquals(2, inventory.getPowerupCount("vest"));
		assertEquals(3, inventory.getPowerupCount("bottle"));
	}
	
	@Test
	public void repOkCheck() throws Exception {
		assertEquals(true, inventory.repOk());
	}


}
