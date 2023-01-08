package Domain.Player;

import java.util.HashMap;
import java.util.HashSet;

public class Inventory {
    /*
     *  OVERVIEW: 
     *  		This class holds a hash-map that stores the collected power-up counts
     *  		that can be activated later in the game according to users will.
     *  		These are the hint, protection-vest and bottle power-ups
     *  		An instance of this class is in the PlayerState object.
     *  		Also, the PlayerController manipulates this class through the PlayerState.
	 *
	 *  Abstract Function:
	 *  		AC(c) = {c.powerupsMap.get(powtype) | c.powerupList.contains(powtype)}
	 *
	 *  The Representation Invariant:
	 *	        (c.powerupsMap not null) &&
     *	        (c.powerupsMap.size() == c.powerupList.length) &&
     * 	        (there are no duplicates in c.powerupList) &&
     *			(c.powerupsMap includes all c.powerupList[0]...c.powerupList[size-1] as keys) &&
     *			(all c.powerupsMap.get(c.powerupList[0])...c.powerupsMap.get(c.pwerupList[size-1]) are non-negative)
	 */
	
	private HashMap<String, Integer> powerupsMap;
    private static final String[] powerupList = {"hint", "vest", "bottle"};

	public Inventory() {
        powerupsMap = new HashMap<String, Integer>();
        for (String powerup : powerupList) {
            powerupsMap.put(powerup, 0);
        }
	}

	public String getPowerup(String powerupType) {
		/*
		 * returns the power-up type as a string if its count is 
		 * larger than 0 in the inventory or returns null otherwise.
		 */
		boolean powerupFound = checkInventory(powerupType);
		if(powerupFound){
            return powerupType;
        }
        return null;
	}

    public int getPowerupCount(String powerupType) {
		/*
		 * returns the power-up count that exists in the inventory
		 */
		boolean powerupFound = checkInventory(powerupType);
		if(powerupFound){
            return powerupsMap.get(powerupType);
        }
        return 0;
	}

    public boolean checkInventory(String item) {
    	/*
    	 * This method returns true if a power-up exists in the 
    	 * inventory and has a count greater than or equal to 1
    	 */
		if(!powerupsMap.containsKey(item)){
            return false;
        }else if(powerupsMap.get(item)==0){
            return false;
        }else{
            return true;
        } 		
	}

    public void incrementPowerups(String powerupType) throws Exception {
    	/*
    	 * This method increments the count of a power-up if it is a valid collectable power-up
    	 */
        if(!powerupsMap.containsKey(powerupType)){
            throw new Exception("Couldn't find powerup type");
        }
        int totalPowerups = powerupsMap.get(powerupType);
        powerupsMap.put(powerupType,++totalPowerups);
    }

    public void decrementPowerups(String powerupType) throws Exception {
    	/*
    	 * This method decrements the count of a power-up if it exists in the inventory
    	 */
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
    	/*
    	 * This is the repOk method that checks the powerupsMap and the powerup counts
    	 */
        if (powerupsMap == null) return false;
        if (powerupsMap.size() != powerupList.length) return false;
        if (duplicates(powerupList)) return false;
        for (int i = 0; i < powerupList.length ; i++) {
            if (!powerupsMap.containsKey(powerupList[i])) return false;
            if (powerupsMap.get(powerupList[i]) < 0) return false;
        }
        return true;
    }
    

    boolean duplicates(final String[] list){
    	/*
    	 * This method is used in the repOk function to check for the duplicates in the powerupList
    	 */
	    HashSet<String> lump = new HashSet<String>();
	    for (String i : list){
	    	if (lump.contains(i)) return true;
	        lump.add(i);
	        }
	    return false;
	}
}
