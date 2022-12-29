package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Domain.Controllers.GameController;
import Domain.Controllers.PlayerController;
import Domain.Game.Location;

public class MoveTest {
	
	private GameController escapeFromKocGame;
	private PlayerController player = new PlayerController();
	private int width = player.getAvatar().getWidth();

	@Test
	public void moveLeftCollisionTest() {
		player.getAvatar().setLocation(60, 60);
		
		Location initLocation = player.getAvatar().getLocation();
		double initX = initLocation.getXLocation();
		double initY = initLocation.getYLocation();
		
		player.getAvatar().move("left");
		
		Location nextLocation = player.getAvatar().getLocation();
		double nextX = nextLocation.getXLocation();
		double nextY = nextLocation.getYLocation();
		
		System.out.println(initX + "," +  initY + "," + nextX + "," +  nextY);
		assertEquals(initX, nextX, 0);
		assertEquals(initY, nextY, 0);		
		
	}
	
	@Test
	public void moveRightCollisionTest() {
		player.getAvatar().setLocation(500-width, 500-width);
		
		Location initLocation = player.getAvatar().getLocation();
		double initX = initLocation.getXLocation();
		double initY = initLocation.getYLocation();
		
		player.getAvatar().move("right");
		
		Location nextLocation = player.getAvatar().getLocation();
		double nextX = nextLocation.getXLocation();
		double nextY = nextLocation.getYLocation();
		
		System.out.println(initX + "," +  initY + "," + nextX + "," +  nextY);
		assertEquals(initX, nextX, 0);
		assertEquals(initY, nextY, 0);		
		
	}
	
	@Test
	public void moveUpCollisionTest() {
		player.getAvatar().setLocation(60, 60);
		
		Location initLocation = player.getAvatar().getLocation();
		double initX = initLocation.getXLocation();
		double initY = initLocation.getYLocation();
		
		player.getAvatar().move("up");
		
		Location nextLocation = player.getAvatar().getLocation();
		double nextX = nextLocation.getXLocation();
		double nextY = nextLocation.getYLocation();
		
		System.out.println(initX + "," +  initY + "," + nextX + "," +  nextY);
		assertEquals(initX, nextX, 0);
		assertEquals(initY, nextY, 0);		
		
	}
	
	@Test
	public void moveDownCollisionTest() {
		player.getAvatar().setLocation(500-width, 500-width);
		
		Location initLocation = player.getAvatar().getLocation();
		double initX = initLocation.getXLocation();
		double initY = initLocation.getYLocation();
		
		player.getAvatar().move("down");
		
		Location nextLocation = player.getAvatar().getLocation();
		double nextX = nextLocation.getXLocation();
		double nextY = nextLocation.getYLocation();
		
		System.out.println(initX + "," +  initY + "," + nextX + "," +  nextY);
		assertEquals(initX, nextX, 0);
		assertEquals(initY, nextY, 0);		
		
	}
	
	@Test
	public void moveSuccessTest() {
		System.out.println("---------------------------------------------------------------");
		player.getAvatar().setLocation(60, 60);
		
		Location initLocation = player.getAvatar().getLocation();
		double initX = initLocation.getXLocation();
		double initY = initLocation.getYLocation();
		
		player.getAvatar().move("down");
		
		Location nextLocation1 = player.getAvatar().getLocation();
		double nextX1 = nextLocation1.getXLocation();
		double nextY1 = nextLocation1.getYLocation();
		
		System.out.println(nextX1 + "," +  nextY1);
		assertEquals(initX, nextX1, 0);
		assertNotEquals(initY, nextY1);	
		
		player.getAvatar().move("right");
		
		Location nextLocation2 = player.getAvatar().getLocation();
		double nextX2 = nextLocation2.getXLocation();
		double nextY2 = nextLocation2.getYLocation();
		
		System.out.println(nextX2 + "," +  nextY2);
		assertNotEquals(nextX1, nextX2);	
		
		player.getAvatar().move("left");
		
		Location nextLocation3 = player.getAvatar().getLocation();
		double nextX3 = nextLocation3.getXLocation();
		double nextY3 = nextLocation3.getYLocation();
		
		System.out.println(nextX3 + "," +  nextY3);
		assertNotEquals(nextX2, nextX3);	
		
		player.getAvatar().move("up");
		
		Location nextLocation4 = player.getAvatar().getLocation();
		double nextX4 = nextLocation4.getXLocation();
		double nextY4 = nextLocation4.getYLocation();
		
		System.out.println(nextX4 + "," +  nextY4);
		assertNotEquals(nextY3, nextY4);	
		
		assertEquals(initX, nextX4, 0);
		assertEquals(initY, nextY4, 0);
		
	}

}
