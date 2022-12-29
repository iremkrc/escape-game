package Domain.Player;

import java.util.HashMap;

public class Inventory {
    // OVERVIEW: 
	private HashMap<String, Integer> powerupsMap;
    private static final String[] powerupList = {"hint", "vest", "bottle"};

	public Inventory() {
        HashMap<String, Integer> powerupsMap = new HashMap<String, Integer>();
        for (String powerup : powerupList) {
            powerupsMap.put(powerup, 0);
        }
	}

	public String getPowerup(String powerupType) {
		boolean powerupFound = checkInventory(powerupType);
		if(powerupFound){
            return powerupType;
        }
        return null;
	}

    public int getPowerupCount(String powerupType) {
		boolean powerupFound = checkInventory(powerupType);
		if(powerupFound){
            return powerupsMap.get(powerupType);
        }
        return 0;
	}

    public boolean checkInventory(String item) {
		if(!powerupsMap.containsKey(item)){
            return false;
        }else if(powerupsMap.get(item)==0){
            return false;
        }else{
            return true;
        } 		
	}

    public void incrementPowerups(String powerupType) throws Exception {
   
        if(!powerupsMap.containsKey(powerupType)){
            throw new Exception("Couldn't find powerup type");
        }
        int totalPowerups = powerupsMap.get(powerupType);
        powerupsMap.put(powerupType,++totalPowerups);
    }

    public void decrementPowerups(String powerupType) throws Exception {
   
        if(!powerupsMap.containsKey(powerupType)){
            throw new Exception("Couldn't find powerup type.");
        }
        int totalPowerups = powerupsMap.get(powerupType);
        powerupsMap.put(powerupType,--totalPowerups);
    }

    public HashMap<String,Integer> getPowerupsMap() {
        return this.powerupsMap;
    }

    public void setPowerupsMap(HashMap<String,Integer> powerupsMap) {
        this.powerupsMap = powerupsMap;
    }
    

    public boolean repOk() {
        if (powerupsMap == null) return false;
        if (powerupsMap.size() != powerupList.length) return false;
        for (int i = 0; i < powerupList.length ; i++) {
            if (!powerupsMap.containsKey(powerupList[i])) return false;
            if(powerupsMap.get(powerupList[i]) < 0) return false;
        }
        return true;
    }
}
