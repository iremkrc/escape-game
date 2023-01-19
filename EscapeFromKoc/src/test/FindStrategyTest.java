package test;

import Domain.Alien.TimeWastingAlien;
import Domain.Controllers.AlienController;
import Domain.Controllers.GameController;
import Domain.Controllers.PlayerController;
import Domain.Controllers.PowerupController;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class FindStrategyTest {
    // This class implements tests to check the "findStrategy" method inside the TimeWastingAlien class
    // Black-Box and GlassBox techniques are used in all test cases.
    TimeWastingAlien alien;
    int totalTime;
    int remainingTime;
    GameController game;

     // This creates a Time-Wasting Alien and sets the total time to 100
    @org.junit.Before
    public void setUp() throws Exception {
        
        totalTime = 100;
        game = GameController.getInstance();
        game.setPlayer(new PlayerController());
        game.setAlienController(new AlienController());
        game.setPowerupController(new PowerupController());
        alien = new TimeWastingAlien();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    // This is a successful scenario that remaining time is more than 70% of the total time
    // and it the method sets the strategy of Time-Wasting Alien to Challenging Strategy
    @org.junit.Test
    public void challengingStrategyTest() {
        remainingTime = 80;
        assertTrue(remainingTime <= totalTime);
        alien.findStrategy(totalTime, remainingTime);
        String type = alien.getStrategy().getType();
        assertEquals("ChallengingStrategy", type);
    }

    // This is a successful scenario that remaining time is between %30 and 70% of the total time
    // and it the method sets the strategy of Time-Wasting Alien to Confused Strategy
    @org.junit.Test
    public void confusedStrategyTest() {
        remainingTime = 50;
        assertTrue(remainingTime <= totalTime);
        alien.findStrategy(totalTime, remainingTime);
        String type = alien.getStrategy().getType();
        assertEquals("ConfusedStrategy", type);
    }

    // This is a successful scenario that remaining time is less than 30% of the total time
    // and it the method sets the strategy of Time-Wasting Alien to Limited Strategy
    @org.junit.Test
    public void limitedStrategyTest() {
        remainingTime = 20;
        assertTrue(remainingTime <= totalTime);
        alien.findStrategy(totalTime, remainingTime);
        String type = alien.getStrategy().getType();
        assertEquals("LimitedStrategy", type);
    }

    // This is a successful scenario that remaining time is more than 70% of the total time at first.
    // Then, remaining time decreases by 4 seconds and it becomes between %30 and 70% of the total time.
    // Finally, the method sets the strategy of Time-Wasting Alien to Confused Strategy.
    @org.junit.Test
    public void challengingToConfusedTest() throws InterruptedException {
        remainingTime = 72;
        assertTrue(remainingTime <= totalTime);
        alien.findStrategy(totalTime, remainingTime);
        remainingTime -= 4;
        alien.findStrategy(totalTime, remainingTime);
        String type = alien.getStrategy().getType();
        assertEquals("ConfusedStrategy", type);
    }

    // This is a successful scenario that remaining time is between %30 and 70% of the total time at first.
    // Then, remaining time decreases by 7 seconds and it becomes less than %30 of the total time.
    // Finally, the method sets the strategy of Time-Wasting Alien to Limited Strategy.
    @org.junit.Test
    public void confusedToLimitedTest() throws InterruptedException {
        remainingTime = 33;
        assertTrue(remainingTime <= totalTime);
        alien.findStrategy(totalTime, remainingTime);
        remainingTime -= 7;
        alien.findStrategy(totalTime, remainingTime);
        String type = alien.getStrategy().getType();
        assertEquals("LimitedStrategy", type);
    }

}