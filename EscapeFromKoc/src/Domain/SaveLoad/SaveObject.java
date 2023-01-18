package Domain.SaveLoad;

import Domain.Alien.Alien;
import Domain.Alien.TimeWastingAlien;
import Domain.Alien.TimeWastingStrategy;
import Domain.Controllers.GameController;
import Domain.Controllers.LoginController;
import Domain.Game.Building;
import Domain.Game.Location;
import Domain.Player.Inventory;

import com.google.gson.*;

import java.util.HashMap;
import Domain.GameObjects.Powerups.IPowerup;

import org.bson.Document;

import java.util.LinkedList;
import java.util.Set;

public class SaveObject {

    private GameController currentGame;
    public LoginController login;
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
        
        int health = currentGame.getPlayerHealth();
        save.addProperty("health", health);
        
        Inventory inventory = currentGame.getPlayerState().getInventory();
        HashMap<String, Integer> powerupsMap = inventory.getPowerupsMap();        
        Set<String> keySet = powerupsMap.keySet();
        String arr[] = new String[3]; 
        int i = 0; 
        for (String x : keySet) 
            arr[i++] = x; 
        int hintNo = 0;
        int vestNo = 0;
        int bottleNo = 0;
        for(int j = 0; j < keySet.size(); j++) {
        	if(j==0) {
        		hintNo = powerupsMap.get(arr[j]);
        	}
        	if(j==1) {
        		vestNo = powerupsMap.get(arr[j]);
        	}
        	if(j==2) {
        		bottleNo = powerupsMap.get(arr[j]);
        	}
        }
        
        System.out.println(keySet);
        System.out.println(hintNo + "|" + vestNo + "|" + bottleNo);
        
        save.addProperty("hintNo", hintNo);
        save.addProperty("vestNo", vestNo);
        save.addProperty("bottleNo", bottleNo);
        
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
                if(currentGame.getAlienController().getAlien().getType() == "TimeWasting") {
                	String currentStrategy = ((TimeWastingAlien) alien).getStrategy().getType();
                	save.addProperty("wastingStrategy", currentStrategy);
                }
            }
        }else {
        	save.addProperty("alienExists", 0);
        	save.addProperty("alien", "noAlien");
        	save.addProperty("alienLocX", 0);
            save.addProperty("alienLocY", 0);
            save.addProperty("wastingStrategy", "none");
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
        
        save.addProperty("isHintActive", currentGame.getGameState().getHintActive());
        save.addProperty("isBottleActive", currentGame.getGameState().getIsBottlePowerupActive());
        save.addProperty("isVestActive", currentGame.getGameState().getIsVestPowerupActive());
        save.addProperty("isKeyFound", currentGame.getGameState().isKeyFound());
        save.addProperty("isDoorOpen", currentGame.getCurrentBuilding().getDoor().getIsOpen());
        
        
        System.out.println(save);

        return save;
    }
    
	public Document toDBObject() {
		System.out.println("thisis some save \n\n" + this.generateSaveJson().toString() + " \n here ends ");
		JsonObject temp = this.generateSaveJson();
		return Document.parse(temp.toString()); // this returns null thus it does not work 
													/// solve here and it will work. 
	}
}
