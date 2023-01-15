package Domain.SaveLoad;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import Domain.Controllers.GameController;
import Domain.Game.Building;
import Domain.Game.Door;
import Domain.Game.GameState;
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
    private GameState gameState;
    public FileSaveLoadAdapter() {
	   	System.out.println("I am about to FileSaveLoad...");
        this.fileSaveLoad = new FileSaveLoad();
        this.currSave = new SaveObject();
        this.game = GameController.getInstance();
        this.gameState = new GameState();
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

            LinkedList<GameObject> tempGameObjectList = new LinkedList<GameObject>();
            for (JsonElement dummyObject : objTemp.getAsJsonArray("gameObjectList")) {
                tempGameObjectList.add(gson.fromJson(dummyObject.getAsJsonObject(),GameObject.class));
            }
            tempBuilding.setGameObjectList(tempGameObjectList);
            
            // set current object count
            tempBuilding.setCurrentObjectCount(objTemp.get("currentObjectCount").getAsInt());
            
            // set IntendedObjectCount
            tempBuilding.setIntendedObjectCount(objTemp.get("intendedObjectCount").getAsInt());
            
            // set door data
            tempBuilding.setDoor(gson.fromJson(objTemp.get("door"), Door.class));
            
            // set availability matrix
            tempBuilding.setGridAvaliabilityMatrix(gson.fromJson(objTemp.get("gridNonAvailability"), int[][].class));
            
            //set isFull 
            tempBuilding.setIsFull(objTemp.get("isFull").getAsBoolean());
            
            //set is door open
            tempBuilding.setDoorState(objTemp.get("doorOpen").getAsBoolean());
            
            // set width 
            tempBuilding.setWidth(objTemp.get("width").getAsInt());
            
            // set height 
            tempBuilding.setHeight(objTemp.get("height").getAsInt());
            
            // set grid size
            tempBuilding.setGridSize(objTemp.get("gridSize").getAsInt());
            
            // set building name 
            tempBuilding.setBuildingName(objTemp.get("buildingName").getAsString());
            
            buildingIndex++;
            
            //buildingList.add(new Building(objTemp.get("buildingName").getAsString(), objTemp.get("currentObjectCount").getAsInt()));
        }
        
        // set at what building did the user left off
        this.gameState.setCurrentBuildingIndex(jo.get("currentBuildingIndex").getAsInt()); // this is where I left off, 
        																					// I save game only exit button is clicked at the moment. 
        																					// May be also just after building mode. 
        																					// This line also is not working as I expect. 
        //game.setGameState(gameState);
        System.out.println(jo.get("currentBuildingIndex").getAsInt());
        
        System.out.println("\n Building size is"+game.getBuildings().size());
        game.initializeRunningMode();
        //game.setCurrentBuilding(0);
        //game.setBuildings(buildingList);

        /*
        JsonArray buildingsObj = (JsonArray) jo.getAsJsonArray("building_mode_data");
        //CopyOnWriteArrayList<Building> onScreenbuildingList = new CopyOnWriteArrayList<Building>();
        JsonObject first_building = buildingsObj.get(0).getAsJsonObject();
        System.out.println("buildingsObj is: " + first_building.get("buildingName"));
        */

    }

}