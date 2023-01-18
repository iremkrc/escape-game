package Domain.SaveLoad;

import Domain.Controllers.GameController;
import Domain.Controllers.LoginController;
import Domain.Game.Building;
import Domain.Game.PlayerState;
import Domain.Player.Inventory;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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