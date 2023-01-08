package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Domain.Controllers.GameController;
import Domain.Controllers.PlayerController;
import Domain.Controllers.PowerupController;
import Domain.Game.Location;
import Domain.GameObjects.Powerups.IPowerup;
import Domain.GameObjects.Powerups.PowerupFactory;

public class PowerupTest {

    private GameController escapeFromKocGame = new GameController();
    private PlayerController player = new PlayerController();
    private PowerupController powerupController = new PowerupController();
    Location location;
    
	// Black-box testing

    @Test
    public void catchTimePowerupTest() throws Exception{
    	
    	// Requires: player wants to catch time power-up 
    	// Modifies: game time
    	// Effects: increments game time by 5 
    	
    	IPowerup timePowerup = PowerupFactory.getInstance().createPowerup("time");
    	timePowerup.setLocation(200,100,25,25);
    	
    	escapeFromKocGame.setPowerupController(powerupController);
    	escapeFromKocGame.setPlayer(player); 
    	powerupController.setPowerup(timePowerup);
    	
    	int initTime = escapeFromKocGame.getGameState().getTime();
    	escapeFromKocGame.catchPowerUp(200, 100);
    	int afterTime = escapeFromKocGame.getGameState().getTime();

        assertEquals(afterTime, initTime+5);
        
    }


    @Test
    public void catchLifePowerupTest() throws Exception{

    	// Requires: player wants to catch life power-up 
    	// Modifies: player's health
    	// Effects: changes the health of the player, increments it 
    	
    	IPowerup lifePowerup = PowerupFactory.getInstance().createPowerup("life");
    	lifePowerup.setLocation(200,100,25,25);
    	
    	escapeFromKocGame.setPowerupController(powerupController); 
    	escapeFromKocGame.setPlayer(player); 
    	powerupController.setPowerup(lifePowerup);  
    	
    	int initHealth = player.getPlayerState().getHealth();
    	escapeFromKocGame.catchPowerUp(200, 100);
 
        assertEquals(player.getPlayerState().getHealth(), initHealth+1);
    }
   
   
    @Test
    public void checkDeletePowerupTest() throws Exception{

    	// Requires: player wants to catch vest power-up 
    	// Modifies: sets power-up to null
    	// Effects: the collected power-up is now set to null, it is deleted
    	
    	IPowerup vestPowerup = PowerupFactory.getInstance().createPowerup("vest");
    	vestPowerup.setLocation(200,100,25,25);
    	
    	escapeFromKocGame.setPowerupController(powerupController); 
    	escapeFromKocGame.setPlayer(player); 
    	powerupController.setPowerup(vestPowerup);  
    
    	escapeFromKocGame.catchPowerUp(200, 100);
 
        assertEquals(powerupController.getPowerup(), null);

    }

    @Test
    public void checkNotBetweenCoordinatesTest() throws Exception{

    	// Requires: player wants to catch life power-up 
    	// Modifies: doesn't modify health
    	// Effects: health doesn't change as the power-up is not collected since it is not next to the player
    	
    	IPowerup lifePowerup = PowerupFactory.getInstance().createPowerup("life"); // or for any other power-up type
    	lifePowerup.setLocation(200,100,25,25);
    	
    	escapeFromKocGame.setPowerupController(powerupController); 
    	escapeFromKocGame.setPlayer(player); 
    	powerupController.setPowerup(lifePowerup);  
    	
    	int initHealth = player.getPlayerState().getHealth();
    	escapeFromKocGame.catchPowerUp(0, 10);
    	 
        assertNotEquals(player.getPlayerState().getHealth(), initHealth+1);
    
    }

    @Test
    public void catchBottlePowerupTest() throws Exception{

    	// Requires: player wants to catch bottle power-up 
    	// Modifies: power-up count in the inventory
    	// Effects:  checks if the power-up is incremented by 1 in the inventory
    	
    	IPowerup bottlePowerup = PowerupFactory.getInstance().createPowerup("bottle");
    	bottlePowerup.setLocation(200,100,25,25);
    	
    	escapeFromKocGame.setPowerupController(powerupController); 
    	escapeFromKocGame.setPlayer(player); 
    	powerupController.setPowerup(bottlePowerup);  
    	
    	int bottlePowerupCount = player.getPlayerState().getInventory().getPowerupCount(bottlePowerup.getType());
    	escapeFromKocGame.catchPowerUp(200, 100);
		assertEquals(bottlePowerupCount+1, player.getPlayerState().getInventory().getPowerupCount(bottlePowerup.getType()));

    }
    
    @Test
    public void catchVestPowerupTest() throws Exception{

    	// Requires: player wants to catch vest power-up 
    	// Modifies: power-up count in the inventory
    	// Effects: checks if the power-up is incremented by 1 in the inventory
    	
    	IPowerup vestPowerup = PowerupFactory.getInstance().createPowerup("vest");
    	vestPowerup.setLocation(200,100,25,25);
    	
    	escapeFromKocGame.setPowerupController(powerupController); 
    	escapeFromKocGame.setPlayer(player); 
    	powerupController.setPowerup(vestPowerup);  
    	
    	int vestPowerupCount = player.getPlayerState().getInventory().getPowerupCount(vestPowerup.getType());
    	escapeFromKocGame.catchPowerUp(200, 100);
		assertEquals(vestPowerupCount+1, player.getPlayerState().getInventory().getPowerupCount(vestPowerup.getType()));

    }
    
    @Test
    public void catchHintPowerupTest() throws Exception{

    	// Requires: player wants to catch hint power-up 
    	// Modifies: power-up count in the inventory
    	// Effects: checks if the power-up is incremented by 1 in the inventory
    	
    	IPowerup hintPowerup = PowerupFactory.getInstance().createPowerup("hint");
    	hintPowerup.setLocation(200,100,25,25);
    	
    	escapeFromKocGame.setPowerupController(powerupController); 
    	escapeFromKocGame.setPlayer(player); 
    	powerupController.setPowerup(hintPowerup);  
    	
    	int hintPowerupCount = player.getPlayerState().getInventory().getPowerupCount(hintPowerup.getType());
    	escapeFromKocGame.catchPowerUp(200, 100);
		assertEquals(hintPowerupCount+1, player.getPlayerState().getInventory().getPowerupCount(hintPowerup.getType()));

    }
}