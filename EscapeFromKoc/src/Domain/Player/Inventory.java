package Domain.Player;

import java.util.HashMap;

public class Inventory {
	public HashMap<String, Integer> powerupsMap = new HashMap<String, Integer>();

	public Inventory() {
		powerupsMap.put("hint", 0);
		powerupsMap.put("vest", 0);
		powerupsMap.put("bottle", 0);
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
}
