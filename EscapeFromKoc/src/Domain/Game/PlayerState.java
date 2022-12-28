package Domain.Game;

import Domain.Player.Inventory;

public class PlayerState {
    boolean isProtectionVestActive;
    boolean isBottlePowerupActive;
    int health;
    public Inventory inventory;
    int score;
    
    public PlayerState() {
        health = 3;
        score = 0;
        inventory = new Inventory();
        isProtectionVestActive = false;
        isBottlePowerupActive = false;
    }

    public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

    public boolean getIsBottlePowerupActive() {
        return isBottlePowerupActive;
    }

    public void setIsBottlePowerupActive(boolean isBottlePowerupActive) {
        this.isBottlePowerupActive = isBottlePowerupActive;
    }

    public boolean getIsProtectionVestActive() {
        return isProtectionVestActive;
    }

    public void setIsProtectionVestActive(boolean isProtectionVestActive) {
        this.isProtectionVestActive = isProtectionVestActive;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	
}
