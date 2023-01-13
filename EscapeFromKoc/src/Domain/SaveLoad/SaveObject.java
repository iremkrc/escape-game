package Domain.SaveLoad;

import Domain.Controllers.LoginController;
import Domain.Game.Building;
import Domain.GameObjects.GameObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Domain.Controllers.GameController;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
        String buildingObjectsList_0 = gsonBuilder.toJson(buildings);
        JsonArray buildingObjectsListJsonArray_0 = JsonParser.parseString(buildingObjectsList_0).getAsJsonArray();

        save.add("building_mode_data", buildingObjectsListJsonArray_0);

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