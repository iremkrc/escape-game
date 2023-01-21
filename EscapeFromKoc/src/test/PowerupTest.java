package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Domain.Controllers.AlienController;
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
    
	// We used Black-box testing

    @Test
    public void catchTimePowerupTest() throws Exception{
    	
    	//this test tries to catch time power-up    	
    	//the catch powerup method increments game time by 5 
    	
    	GameController game = GameController.getInstance();
        game.setPlayer(new PlayerController());
        game.setAlienController(new AlienController());
        game.setPowerupController(new PowerupController());
    
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

		//this test tries to catch life power-up    	
    	//the catch powerup method increments the life of the player
    	GameController game = GameController.getInstance();
        game.setPlayer(new PlayerController());
        game.setAlienController(new AlienController());
        game.setPowerupController(new PowerupController());
    	
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

		//this test tries to catch vest power-up    	
    	//the catch powerup method collects the powerup and sets it to null, powerup is deleted
    	GameController game = GameController.getInstance();
        game.setPlayer(new PlayerController());
        game.setAlienController(new AlienController());
        game.setPowerupController(new PowerupController());
    	
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

		//this test tries to catch life power-up    	
    	//the catch powerup method is called, but the life of the player is not modified, as the power-up is not collected since it is not next to the player
    	
    	GameController game = GameController.getInstance();
        game.setPlayer(new PlayerController());
        game.setAlienController(new AlienController());
        game.setPowerupController(new PowerupController());
    	
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

		//this test tries to catch bottle power-up    	
    	//checks if the power-up is incremented by 1 in the inventory
    	
    	GameController game = GameController.getInstance();
        game.setPlayer(new PlayerController());
        game.setAlienController(new AlienController());
        game.setPowerupController(new PowerupController());
    	
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

		//this test tries to catch vest power-up    	
    	//checks if the power-up is incremented by 1 in the inventory
    	
    	GameController game = GameController.getInstance();
        game.setPlayer(new PlayerController());
        game.setAlienController(new AlienController());
        game.setPowerupController(new PowerupController());
    	
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

		//this test tries to catch hint power-up    	
    	//checks if the power-up is incremented by 1 in the inventory
    	
    	GameController game = GameController.getInstance();
        game.setPlayer(new PlayerController());
        game.setAlienController(new AlienController());
        game.setPowerupController(new PowerupController());
    	
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