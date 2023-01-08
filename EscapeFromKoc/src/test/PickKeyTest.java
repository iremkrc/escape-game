package test;


import Domain.Controllers.AlienController;
import Domain.Controllers.GameController;
import Domain.Controllers.PlayerController;
import Domain.Controllers.PowerupController;
import Domain.Game.Building;

import Domain.Game.GameState;
import Domain.GameObjects.GameObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class PickKeyTest {
    private static PlayerController player;
    private static GameController game;

    private static GameState gameState;

    private static Building currBuild ;
    private static Building nextBuilding ;
    private static LinkedList<Building> buildings;
    @BeforeAll
    public static void setUp(){

    }

    /**
     * This creates a pseudo game setting it has 2 buildings
     * and for those test avatar is inside Student Center building.
     * If it finds the key successfully it will pass to the CASe building.
     * To find the key player should be on top of the object and it should be clicking on the right place.
     */
    @BeforeEach
    public void everyTime(){
        player = new PlayerController();
        game = GameController.getInstance();
        game.setPlayer(player);
        gameState = new GameState();
        game.setPowerupController(new PowerupController());
        game.setAlienController(new AlienController());

        currBuild = new Building("Student Center",1);
        nextBuilding = new Building("CASE",1);

        currBuild.addObject(70,60);
        currBuild.incrementCurrentObjectCount();
        currBuild.getObjectList().get(0).setContainsKey(true); // add key under the object.
        game.setKeyFound(false);
        GameObject obj = currBuild.getObjectList().get(0);

        buildings = new LinkedList<Building>();
        buildings.add(currBuild);
        buildings.add(nextBuilding);

        game.setBuildings(buildings);
        game.setCurrentBuilding(0);
        game.getPlayer().getAvatar().setLocation(0,0);
    }

    /**
     * This is successfull scenerio user clicks on the object that has the key
     * also it is on top of the object
     *
     */
    @Test
    public void testWithCorrectLocationAndClick() {
        //pickKeyOnTopOfObjectTest
        game.getPlayer().getAvatar().setLocation(75.0, 60.0);
        int x = 72; // mouseX
        int y  = 72; // mouseY
        game.pickKey(x,y); // this should be able to find the key

        // BB Testing
        assertEquals(game.getCurrentBuilding().getBuildingName(), "CASE"); // if key picked new building should be loaded.
    }

    /**
     * This scenerio avatar is not on top of the object but
     * but clicking on top of the object that has the key
     * It still do not pass to next building.
     *

     */
    @Test
    public void testWithWrongAvatarLocation() {
        //pickKeyNotOnTopOfObjectTest
        game.getPlayer().getAvatar().setLocation(450,400);
        int x = 72; // mouseX
        int y  = 72; // mouseY
        game.pickKey(x,y); // this should be able to find the key


        // BB testing
        assertFalse(game.isKeyFound());
        assertNotEquals(game.getCurrentBuilding().getBuildingName(), "CASE"); // key is not picked new building should not be loaded.
        assertEquals(game.getCurrentBuilding().getBuildingName(), "Student Center");
    }

    /**
     * User is on top of the object but clicking on the wrong location
     *
     */
    @Test
    public void testWithWrongMouseClick() {
        //pickKeyOnTopOfObjectClickingAnotherPlaceTest
        game.getPlayer().getAvatar().setLocation(75.0, 60.0);
        int x = 220; // mouseX
        int y  = 320; // mouseY
        game.pickKey(x,y); // this should be able to find the key

        // BB Testing
        assertFalse(game.isKeyFound());
        assertNotEquals(game.getCurrentBuilding().getBuildingName(), "CASE"); // key is not picked new building should not be loaded.
        assertEquals(game.getCurrentBuilding().getBuildingName(), "Student Center");
    }


    /**
     * User is not on top of the object and  clicking on the wrong location
     *
     * Black Box Testing
     *
     */
    @Test
    public void testWithWrongMouseAndAvatarLocation() {
        //pickKeyNotOnTopOfObjectClickingAnotherPlaceTest
        game.getPlayer().getAvatar().setLocation(750.0, 600.0);
        int x = 220; // mouseX
        int y  = 320; // mouseY
        game.pickKey(x,y); // this should be able to find the key

        assertFalse(game.isKeyFound());
        assertNotEquals(game.getCurrentBuilding().getBuildingName(), "CASE"); // key is not picked new building should not be loaded.
        assertEquals(game.getCurrentBuilding().getBuildingName(), "Student Center");
    }

    /**
     * User tries to find the key under an object that actually does not have it.
     *
     */
    @Test
    public void testWithNoKeyContained() {
        //pickKeyFromAnObjectDoesNotHaveItTest
        game.getPlayer().getAvatar().setLocation(75.0, 60.0);
        int x = 72; // mouseX
        int y  = 72; // mouseY

        GameObject g = game.getCurrentBuilding().getObjectList().get(0);
        g.setContainsKey(false);

        game.pickKey(x,y); // this should be able to find the key

        // GB Testing
        assertFalse(game.isKeyFound());
        assertNotEquals(game.getCurrentBuilding().getBuildingName(), "CASE"); // key is not picked new building should not be loaded.
        assertEquals(game.getCurrentBuilding().getBuildingName(), "Student Center");

    }


}