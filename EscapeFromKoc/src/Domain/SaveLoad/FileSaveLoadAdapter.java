package Domain.SaveLoad;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import Domain.Alien.Alien;
import Domain.Controllers.GameController;
import Domain.Game.Building;
import Domain.Game.Door;
import Domain.Game.GameState;
import Domain.GameObjects.GameObject;
import Domain.Game.Location;
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
        this.game.getGameState().setCurrentBuildingIndex(jo.get("currentBuildingIndex").getAsInt()); // this is where I left off, 
		this.game.setCurrentBuilding(jo.get("currentBuildingIndex").getAsInt());
        // I save game only exit button is clicked at the moment. 
		// May be also just after building mode. 
		// This line also is not working as I expect. 

        this.game.getGameState().setTime(jo.get("time").getAsInt());
                
        this.game.getPlayerState().setHealth(jo.get("health").getAsInt());
        
        this.game.getPlayerState().getInventory().getPowerupsMap().put("hint", jo.get("hintNo").getAsInt());
        
        this.game.getPlayerState().getInventory().getPowerupsMap().put("vest", jo.get("vestNo").getAsInt());
        
        this.game.getPlayerState().getInventory().getPowerupsMap().put("bottle", jo.get("bottleNo").getAsInt());


        System.out.println("\n Building size is"+game.getBuildings().size());
        
        double avatarx = jo.get("avatarLocX").getAsInt();
        double avatary = jo.get("avatarLocY").getAsInt();

        this.game.getPlayer().getAvatar().setLocation(avatarx, avatary);
        System.out.println(game.getPlayer().getAvatar().getLocation().xGrid);
        
        this.game.getAlienController().setAlienTime(jo.get("alienTime").getAsLong());
        this.game.getPlayerState().setHealth(jo.get("health").getAsInt());
        
        int alienExists = jo.get("alienExists").getAsInt();
        if(alienExists == 1) {
        	this.game.getAlienController().setAlien(game.getAlienController().getAlienFactory().createAlien(jo.get("alien").getAsString()));
        	this.game.getAlienController().getAlien().setLocation(new Location(jo.get("alienLocX").getAsDouble(),jo.get("alienLocY").getAsDouble()));
        }
        
        this.game.getPowerupController().setPowerupTime(jo.get("powerupTime").getAsInt());
        this.game.getPowerupController().setPowerupBoolean(jo.get("powerupBoolean").getAsBoolean());
                
        int powerupExists = jo.get("powerupExists").getAsInt();
        if(powerupExists == 1) {
        	this.game.getPowerupController().setPowerup(game.getPowerupController().getPowerupFactory().createPowerup(jo.get("powerup").getAsString()));
        	this.game.getPowerupController().getPowerup().setLocation(new Location(jo.get("powerupLocX").getAsDouble(),jo.get("powerupLocY").getAsDouble()));
        }

        
    }
    
}