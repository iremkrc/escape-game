package Domain.SaveLoad;

import Domain.Alien.Alien;
import Domain.Controllers.GameController;
import Domain.Controllers.LoginController;
import Domain.Game.Building;
import Domain.Game.Location;
import Domain.Game.PlayerState;
import Domain.GameObjects.Powerups.IPowerup;

import com.google.gson.*;

import java.awt.Color;
import java.util.LinkedList;

public class SaveObject {

    private GameController currentGame;
    private LoginController login;
    private String loginName;

    public SaveObject() {
        GameController game = GameController.getInstance();
        this.login = new LoginController(game);
        this.currentGame = game;
        this.loginName = login.getLoginName();
    }
   
    public JsonObject generateSaveJson() {

        JsonObject save = new JsonObject();
        Gson gsonBuilder = new GsonBuilder().create();
        JsonParser jsonParser = new JsonParser();

        save.addProperty("playerName", loginName);

        // add building mode data, objects located at what positions
        LinkedList<Building> buildings = currentGame.getBuildings();
        String buildingObjectsList_0 = gsonBuilder.toJson(buildings);//////////////ERROR ERROR ERROR ///////////////
        JsonArray buildingObjectsListJsonArray_0 = JsonParser.parseString(buildingObjectsList_0).getAsJsonArray();
        save.add("building_mode_data", buildingObjectsListJsonArray_0);        
        
        int currentBuildingIndex = currentGame.getCurrentBuildingIndex();
        save.addProperty("currentBuildingIndex", currentBuildingIndex);
        
        int second = currentGame.getGameState().getTime();
        System.out.println(second);
        save.addProperty("time", second);
        
        
        double avatarLocX = currentGame.getPlayer().getAvatar().getLocation().xLocation;
        double avatarLocY = currentGame.getPlayer().getAvatar().getLocation().yLocation;
        System.out.println(second);
        save.addProperty("avatarLocX", avatarLocX);
        save.addProperty("avatarLocY", avatarLocY);
        save.addProperty("alienTime", currentGame.getAlienController().getAlienTime());

        Alien alien = currentGame.getAlienController().getAlien();
        if(alien != null){
            if(!alien.isEmpty()) {
            	save.addProperty("alienExists", 1);
            	save.addProperty("alien", currentGame.getAlienController().getAlien().getType());
            	Location alienLoc = alien.getLocation();
            	save.addProperty("alienLocX", alienLoc.getXLocation());
                save.addProperty("alienLocY", alienLoc.getYLocation());
            }
        }else {
        	save.addProperty("alienExists", 0);
        	save.addProperty("alien", "noAlien");
        	save.addProperty("alienLocX", 0);
            save.addProperty("alienLocY", 0);
        }
        
        save.addProperty("powerupTime", currentGame.getPowerupController().getPowerupTime());
        save.addProperty("powerupBoolean", currentGame.getPowerupController().getPowerupBoolean());

        IPowerup powerup = currentGame.getPowerupController().getPowerup();
        if(powerup != null){
        	save.addProperty("powerupExists", 1);
        	save.addProperty("powerup", currentGame.getPowerupController().getPowerup().getType());
        	Location powerupLoc = powerup.getLocation();
        	save.addProperty("powerupLocX", powerupLoc.getXLocation());
            save.addProperty("powerupLocY", powerupLoc.getYLocation());
        }else {
        	save.addProperty("powerupExists", 0);
        	save.addProperty("powerup", "noPowerup");
        	save.addProperty("powerupLocX", 0);
            save.addProperty("powerupLocY", 0);
        }
        
        
        int health = currentGame.getPlayerHealth();
        save.addProperty("health", health);
        
        System.out.println(save);

        return save;
    }

}

/*
    for(Building b: buildings) {
        LinkedList<GameObject> buildingObjects = b.getObjectList();
        String buildingObjectsList = gsonBuilder.toJson(buildingObjects);
        JsonArray buildingObjectsListJsonArray = JsonParser.parseString(buildingObjectsList).getAsJsonArray();
        save.add(b.getBuildingName(), buildingObjectsListJsonArray);
        }
 */