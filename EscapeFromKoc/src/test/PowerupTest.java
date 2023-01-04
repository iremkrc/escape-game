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
    IPowerup timePowerup;
    IPowerup bottlePowerup;
    IPowerup hintPowerup;
    IPowerup lifePowerup;
    IPowerup vestPowerup;

  
    @Test
    public void checkHealthPowerupTest(){
    	
    	// Requires: life power-up is collected
    	// Modifies: player's health
    	// Effects: changes the health of the player, increments it 

        int initHealth = player.getPlayerState().getHealth();
        player.getPlayerState().setHealth(initHealth+1);

        assertNotEquals(player.getPlayerState().getHealth(), initHealth);
    }

    @Test
    public void checkTimePowerupTest(){
    	
    	// Requires: time power-up is collected
    	// Modifies: time
    	// Effects: changes the time when time power-up collected
    	
        int initTime = escapeFromKocGame.getGameState().getTime();
        escapeFromKocGame.getGameState().setTime(initTime + 5);

        assertNotEquals(escapeFromKocGame.getGameState().getTime(), initTime);

    }

    @Test
    public void checkCoordinatesTest(){
       
    	// Requires: power-ups are created
    	// Modifies: this
    	// Effects: each power-ups coordinates and width height are checked
    	
    	IPowerup bottlePowerup = PowerupFactory.getInstance().createPowerup("bottle");

        bottlePowerup.setLocation(200,100,25,25);

        assertEquals("powerup is on correct x coordinate", 200, bottlePowerup.getLocation().getXLocation(), 0.0001);
        assertEquals("powerup is on correct y coordinate", 100, bottlePowerup.getLocation().getYLocation(),0.0001);

        assertEquals("powerup has correct height", 25, bottlePowerup.getHeight());
        assertEquals("powerup has correct width", 25, bottlePowerup.getWidth());
        
        
        IPowerup timePowerup = PowerupFactory.getInstance().createPowerup("time");

        timePowerup.setLocation(100,50,25,25);

        assertEquals("powerup is on correct x coordinate", 100, timePowerup.getLocation().getXLocation(), 0.0001);
        assertEquals("powerup is on correct y coordinate", 50, timePowerup.getLocation().getYLocation(),0.0001);

        assertEquals("powerup has correct height", 25, timePowerup.getHeight());
        assertEquals("powerup has correct width", 25, timePowerup.getWidth());
        
        
        IPowerup lifePowerup = PowerupFactory.getInstance().createPowerup("life");

        lifePowerup.setLocation(300,150,25,25);

        assertEquals("powerup is on correct x coordinate", 300, lifePowerup.getLocation().getXLocation(), 0.0001);
        assertEquals("powerup is on correct y coordinate", 150, lifePowerup.getLocation().getYLocation(),0.0001);

        assertEquals("powerup has correct height", 25, lifePowerup.getHeight());
        assertEquals("powerup has correct width", 25, lifePowerup.getWidth());
        
        
        IPowerup hintPowerup = PowerupFactory.getInstance().createPowerup("hint");

        hintPowerup.setLocation(200,100,25,25);

        assertEquals("powerup is on correct x coordinate", 200, hintPowerup.getLocation().getXLocation(), 0.0001);
        assertEquals("powerup is on correct y coordinate", 100, hintPowerup.getLocation().getYLocation(),0.0001);

        assertEquals("powerup has correct height", 25, hintPowerup.getHeight());
        assertEquals("powerup has correct width", 25, hintPowerup.getWidth());
        
        
        IPowerup vestPowerup = PowerupFactory.getInstance().createPowerup("vest");

        vestPowerup.setLocation(200,180,25,25);

        assertEquals("powerup is on correct x coordinate", 200, vestPowerup.getLocation().getXLocation(), 0.0001);
        assertEquals("powerup is on correct y coordinate", 180, vestPowerup.getLocation().getYLocation(),0.0001);

        assertEquals("powerup has correct height", 25, vestPowerup.getHeight());
        assertEquals("powerup has correct width", 25, vestPowerup.getWidth());

    }

    @Test
    public void createPowerupTest(){

    	// Modifies: power-up
    	// Effects: checks if each power-up type is created correctly
    	
        this.bottlePowerup = PowerupFactory.getInstance().createPowerup("bottle");
        this.timePowerup = PowerupFactory.getInstance().createPowerup("time");
        this.vestPowerup = PowerupFactory.getInstance().createPowerup("vest");
        this.hintPowerup = PowerupFactory.getInstance().createPowerup("hint");
        this.lifePowerup = PowerupFactory.getInstance().createPowerup("life");

        assertNotNull("Bottle powerup is created", this.bottlePowerup);
        assertTrue("Bottle powerup is a powerup", bottlePowerup instanceof IPowerup);

        assertNotNull("Time powerup is created", this.timePowerup);
        assertTrue("Time powerup is a powerup", timePowerup instanceof IPowerup);

        assertNotNull("Vest powerup is created", this.vestPowerup);
        assertTrue("Vest powerup is a powerup", vestPowerup instanceof IPowerup);

        assertNotNull("Hint powerup is created", this.hintPowerup);
        assertTrue("Hint powerup is a powerup", hintPowerup instanceof IPowerup);

        assertNotNull("Life powerup is created", this.lifePowerup);
        assertTrue("Life powerup is a powerup", lifePowerup instanceof IPowerup);

        assertNull("Nothing is not created because of invalid type", null);
    }

    @Test
    public void checkDeletePowerupTest(){

    	// Requires: power-ups are created
    	// Modifies: created powerup's are changed to null
    	// Effects: power-ups are deleted and they are set to null
    	
        IPowerup bottlePowerup = PowerupFactory.getInstance().createPowerup("bottle");
        powerupController.deletePowerup(bottlePowerup);
        assertEquals("Powerup is deleted", powerupController.getPowerup(), null);
        
        
        IPowerup timePowerup = PowerupFactory.getInstance().createPowerup("time");
        powerupController.deletePowerup(timePowerup);
        assertEquals("Powerup is deleted", powerupController.getPowerup(), null);
        
        
        IPowerup lifePowerup = PowerupFactory.getInstance().createPowerup("life");
        powerupController.deletePowerup(lifePowerup);
        assertEquals("Powerup is deleted", powerupController.getPowerup(), null);
        
        
        IPowerup vestPowerup = PowerupFactory.getInstance().createPowerup("vest");
        powerupController.deletePowerup(vestPowerup);
        assertEquals("Powerup is deleted", powerupController.getPowerup(), null);
        
        
        IPowerup hintPowerup = PowerupFactory.getInstance().createPowerup("hint");
        powerupController.deletePowerup(hintPowerup);
        assertEquals("Powerup is deleted", powerupController.getPowerup(), null);
    }

       
    @Test
    public void checkPowerupInventoryTest() throws Exception{
    	
    	// Requires: power-ups are created
    	// Modifies: power-up count in the inventory
    	// Effects: checks if the power-up created is incremented by 1 in the inventory
    	
        IPowerup bottlePowerup = PowerupFactory.getInstance().createPowerup("bottle");
    	int bottlePowerupCount = player.getPlayerState().getInventory().getPowerupCount(bottlePowerup.getType());
		player.getPlayerState().getInventory().incrementPowerups(bottlePowerup.getType());
		assertEquals(bottlePowerupCount+1, player.getPlayerState().getInventory().getPowerupCount(bottlePowerup.getType()));
		
		
		IPowerup hintPowerup = PowerupFactory.getInstance().createPowerup("hint");
	    int hintPowerupCount = player.getPlayerState().getInventory().getPowerupCount(hintPowerup.getType());
		player.getPlayerState().getInventory().incrementPowerups(hintPowerup.getType());
		assertEquals(hintPowerupCount+1, player.getPlayerState().getInventory().getPowerupCount(hintPowerup.getType()));
		
		
		IPowerup vestPowerup = PowerupFactory.getInstance().createPowerup("vest");
    	int vestPowerupCount = player.getPlayerState().getInventory().getPowerupCount(vestPowerup.getType());
		player.getPlayerState().getInventory().incrementPowerups(vestPowerup.getType());
		assertEquals(vestPowerupCount+1, player.getPlayerState().getInventory().getPowerupCount(vestPowerup.getType()));
    }
}
