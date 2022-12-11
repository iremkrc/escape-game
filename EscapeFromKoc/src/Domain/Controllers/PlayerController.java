package Domain.Controllers;

import java.security.Key;
import Domain.Game.PlayerState;
import Domain.Player.Avatar;
import Domain.Player.Inventory;

public class PlayerController {
	public Avatar avatar;
	public Inventory inventory;
	int health;
	int score;
	GameController escapeFromKocGame;	
	PlayerState playerState;

	public PlayerController() {
		inventory = new Inventory();
		avatar = new Avatar(25);   //unitlength
		escapeFromKocGame = GameController.getInstance();	
		playerState = new PlayerState();
		this.health = playerState.getHealth();
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

	public PlayerState getPlayerState() {
		return playerState;
	}

}
