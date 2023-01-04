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

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		player = new PlayerController();
		playerState = new PlayerState();
		inventory = playerState.inventory;
		game = new GameController();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {}

	@BeforeEach
	void setUp() throws Exception {
		//setting initial number of power-ups to random values 
		
		HashMap<String, Integer> powerupMap = inventory.getPowerupsMap();
		powerupMap.replace("hint", 3);
		powerupMap.replace("vest", 3);
		powerupMap.replace("bottle", 3);
	}

	@AfterEach
	void tearDown() throws Exception {}
	
	@Test
	public void powerupNumberCheck() {
		System.out.println("yess");
		//testing whether checkInventory works for power-ups 
		String powerupType = "vest";//or any other powerupType 
		boolean found = inventory.checkInventory(powerupType);
		
		assertEquals(found,inventory.getPowerupsMap().get(powerupType) != 0);
		
		//testing for 0 amount of power-ups 
		inventory.getPowerupsMap().replace(powerupType, 0);
		found = inventory.checkInventory(powerupType);
		assertEquals(found,inventory.getPowerupsMap().get(powerupType) != 0);
	}
	
	@Test
	public void removePowerupCheck() {
		String powerupType = "vest"; //or any other powerupType 
		inventory.getPowerupsMap().replace(powerupType, 1);
		inventory.decrementPowerups(powerupType);
		boolean isFound = inventory.checkInventory(powerupType);
		assertEquals(isFound,inventory.getPowerupsMap().get(powerupType) != 0);

	}
	
	@Test
	public void incrementPowerupCheck() {
		String powerupType = "vest"; //or any other powerupType 
		inventory.getPowerupsMap().replace(powerupType, 0);
		inventory.incrementPowerups(powerupType);
		assertEquals(1, player.getPlayerState().getInventory().getPowerupCount(powerupType));

	}


}
