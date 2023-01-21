package Domain.SaveLoad;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;
import org.bson.json.JsonWriterSettings.Builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Domain.Controllers.GameController;
import Domain.Game.Building;
import Domain.Game.Door;
import Domain.Game.GameState;
import Domain.Alien.TimeWastingAlien;
import Domain.Game.Building;
import Domain.Game.Door;
import Domain.Game.Location;
import Domain.GameObjects.GameObject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoSaveLoadAdapter implements ISaveLoadAdapter {
	private MongoSaveLoad mongoSaveLoad;
	private SaveObject currSave;
    private GameController game;
	
	public MongoSaveLoadAdapter() {
		this.mongoSaveLoad = new MongoSaveLoad();
		this.currSave = new SaveObject();
        this.game = GameController.getInstance();

	}
	
	@Override
	public void save() {
		this.mongoSaveLoad.insert(this.currSave.toDBObject());
	}


	@Override
	public void load() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Document loadDoc = this.mongoSaveLoad.read((String) currSave.toDBObject().get("playerName"));
		
		if (loadDoc.equals(null)) {
			// No document was found with given username
			return;
		}
        
		System.out.println(loadDoc.toString());
		//JsonObject jo = (JsonObject) JsonParser.parseString(loadDoc.toString());
		JsonObject jo = (JsonObject) new JsonParser().parse(loadDoc.toJson());
		// Same operations with file
		
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
        // We save game only exit button is clicked at the moment. 
		// Maybe also just after building mode. 

        this.game.getGameState().setTime(jo.get("time").getAsInt());
        this.game.getPlayerState().setHealth(jo.get("health").getAsInt());
        this.game.getPlayerState().getInventory().getPowerupsMap().put("hint", jo.get("hintNo").getAsInt());
        this.game.getPlayerState().getInventory().getPowerupsMap().put("vest", jo.get("vestNo").getAsInt());
        this.game.getPlayerState().getInventory().getPowerupsMap().put("bottle", jo.get("bottleNo").getAsInt());

        System.out.println("\n Building size is"+game.getBuildings().size());
        
        double avatarx = jo.get("avatarLocX").getAsInt();
        double avatary = jo.get("avatarLocY").getAsInt();
        this.game.getPlayer().getAvatar().setLocation(avatarx, avatary);
        this.game.getAlienController().setAlienTime(jo.get("alienTime").getAsLong());
        this.game.getPlayerState().setHealth(jo.get("health").getAsInt());
        this.game.getPowerupController().setPowerupTime(jo.get("powerupTime").getAsInt());
        this.game.getPowerupController().setPowerupBoolean(jo.get("powerupBoolean").getAsBoolean());
        this.game.getGameState().setIsVestPowerupActive(jo.get("isVestActive").getAsBoolean());
        this.game.getGameState().setKeyFound(jo.get("isKeyFound").getAsBoolean());
        this.game.getGameState().setHintActive(jo.get("isHintActive").getAsBoolean());
        this.game.getGameState().setIsBottlePowerupActive(jo.get("isBottleActive").getAsBoolean());
        this.game.getCurrentBuilding().getDoor().setIsOpen(jo.get("isDoorOpen").getAsBoolean());
        
        int alienExists = jo.get("alienExists").getAsInt();
        if(alienExists == 1) {
        	this.game.getAlienController().setAlien(game.getAlienController().getAlienFactory().createAlien(jo.get("alien").getAsString()));
        	this.game.getAlienController().getAlien().setLocation(new Location(jo.get("alienLocX").getAsDouble(),jo.get("alienLocY").getAsDouble()));
        	if(game.getAlienController().getAlien().getType() == "TimeWasting") {
				((TimeWastingAlien) game.getAlienController().getAlien()).setStrategy(((TimeWastingAlien) game.getAlienController().getAlien()).findStrategy(game.getGameState().getTotalTime(), game.getGameState().getTime()));
            }
        }
            
        int powerupExists = jo.get("powerupExists").getAsInt();
        if(powerupExists == 1) {
        	this.game.getPowerupController().setPowerup(game.getPowerupController().getPowerupFactory().createPowerup(jo.get("powerup").getAsString()));
        	this.game.getPowerupController().getPowerup().setLocation(new Location(jo.get("powerupLocX").getAsDouble(),jo.get("powerupLocY").getAsDouble()));
        }
	}
}