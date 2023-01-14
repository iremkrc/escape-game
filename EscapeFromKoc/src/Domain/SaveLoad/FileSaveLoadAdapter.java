package Domain.SaveLoad;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import Domain.Controllers.GameController;
import Domain.Game.Building;
import Domain.GameObjects.GameObject;
import com.google.gson.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class FileSaveLoadAdapter implements ISaveLoadAdapter {

    private FileSaveLoad fileSaveLoad;
    private SaveObject currSave;
    private GameController game;
    public FileSaveLoadAdapter() {
	   	System.out.println("I am about to FileSaveLoad...");
        this.fileSaveLoad = new FileSaveLoad();
        this.currSave = new SaveObject();
        this.game = GameController.getInstance();
    }

    @Override
    public void save() {
        System.out.println("saved game after building mode... ");
        this.fileSaveLoad.write(currSave);
    }

    @Override
    public void load() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        System.out.println("read  game after login completed... ");
        JsonObject jo = this.fileSaveLoad.read();

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        JsonPrimitive loginName = jo.getAsJsonPrimitive("playerName"); //shooter inventory
        System.out.println("login name is: " + loginName);



        LinkedList<Building> buildingList = game.getBuildings();
        int buildingIndex = 0;
        for (JsonElement at : jo.get("building_mode_data").getAsJsonArray()) {
            Building tempBuilding = buildingList.get(buildingIndex);

            JsonObject objTemp = at.getAsJsonObject(); // every building information,
            System.out.println("gameObjectList is \n" + objTemp.get("gameObjectList") + "\n");

            LinkedList<GameObject> tempGameObjectList = new LinkedList<GameObject>();
            for (JsonElement dummyObject : objTemp.getAsJsonArray("gameObjectList")) {
                tempGameObjectList.add(gson.fromJson(dummyObject.getAsJsonObject(),GameObject.class));
            }
            tempBuilding.setGameObjectList(tempGameObjectList);
            buildingIndex++;

            //buildingList.add(new Building(objTemp.get("buildingName").getAsString(), objTemp.get("currentObjectCount").getAsInt()));
        }
        System.out.println("\n Building size is"+game.getBuildings().size());
        game.initializeRunningMode();
        game.setCurrentBuilding(0);
        //game.setBuildings(buildingList);

        /*
        JsonArray buildingsObj = (JsonArray) jo.getAsJsonArray("building_mode_data");
        //CopyOnWriteArrayList<Building> onScreenbuildingList = new CopyOnWriteArrayList<Building>();
        JsonObject first_building = buildingsObj.get(0).getAsJsonObject();
        System.out.println("buildingsObj is: " + first_building.get("buildingName"));
        */

    }

}