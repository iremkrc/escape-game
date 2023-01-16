package Domain.SaveLoad;

import Domain.Controllers.GameController;
import Domain.Controllers.LoginController;
import Domain.Game.Building;
import Domain.Game.PlayerState;

import com.google.gson.*;

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
        System.out.println(save);
        
        
        int currentBuildingIndex = currentGame.getCurrentBuildingIndex();
        save.addProperty("currentBuildingIndex", currentBuildingIndex);
        
        /* those do not work as I expect because player state is not correctly implemented in game controller. 
        // add player state to saved data 
        PlayerState pState = currentGame.getPlayerState();
        String playerStateData = gsonBuilder.toJson(pState);
        System.out.println("state data is: "+ playerStateData);
        JsonObject pStateObject = JsonParser.parseString(playerStateData).getAsJsonObject();
        save.add("playerState_data", pStateObject);
      	*/
        
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