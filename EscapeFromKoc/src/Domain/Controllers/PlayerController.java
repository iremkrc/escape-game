package Domain.Controllers;

import Domain.Game.PlayerState;
import Domain.Player.Avatar;

import Domain.SaveLoad.FileSaveLoadAdapter;
import Domain.SaveLoad.MongoSaveLoad;
import Domain.SaveLoad.MongoSaveLoadAdapter;

import java.io.FileNotFoundException;


public class PlayerController {
	public Avatar avatar;
	int score;
	GameController escapeFromKocGame;	
	PlayerState playerState;
	private FileSaveLoadAdapter saveLoadService;
	private MongoSaveLoadAdapter mongoSaveLoadService;
	
	public PlayerController() {
		escapeFromKocGame = GameController.getInstance();
		avatar = new Avatar(escapeFromKocGame.getGameState().gridSize); 
		playerState = new PlayerState();
		this.score = playerState.getScore();
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void moveAvatar(String direction) {
		avatar.move(direction);
	}

	public void incrementScore(double increment) {
		score+=increment;
	}

	public void pickPowerup(String type) {

	}

	public void useHintPowerUp() throws Exception{
		if(this.getPlayerState().inventory.checkInventory("hint")){
			this.getPlayerState().inventory.decrementPowerups("hint");
			escapeFromKocGame.getGameState().setHintActive(true);
			System.out.println("Hint Power-up Activated");
		}
	}

	public void useBottlePowerUp() throws Exception{
		if(this.getPlayerState().inventory.checkInventory("bottle")){
			this.getPlayerState().inventory.decrementPowerups("bottle");
			escapeFromKocGame.getGameState().setIsBottlePowerupActive(true);
			System.out.println("Bottle Power-up Activated");
		}
	}
	
	public void useVestPowerUp() throws Exception{
		if(this.getPlayerState().inventory.checkInventory("vest")){
			this.getPlayerState().inventory.decrementPowerups("vest");
			escapeFromKocGame.getGameState().setIsVestPowerupActive(true);
			System.out.println("Protection-Vest Power-up Activated");
		}
	}

	public PlayerState getPlayerState() {
		return playerState;
	}
	
	public void saveGameLocal (){
	   	System.out.println("I am about to FileSaveLoadAdapter...");
		saveLoadService = new FileSaveLoadAdapter();
 
		saveLoadService.save();

	}

	public void loadGameLocal() throws FileNotFoundException {
		saveLoadService = new FileSaveLoadAdapter();
		//new MongoSaveLoad();
		saveLoadService.load();
	}

	public void saveGameDatabase(){
		this.mongoSaveLoadService = new MongoSaveLoadAdapter();
		
		mongoSaveLoadService.save();
	}

	public void loadGameDatabase(){

	}
	
}
