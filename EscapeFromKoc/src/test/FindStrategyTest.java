package test;

import Domain.Alien.TimeWastingAlien;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class FindStrategyTest {

    TimeWastingAlien alien;
    int totalTime;
    int remainingTime;

    @org.junit.Before
    public void setUp() throws Exception {
        alien = new TimeWastingAlien();
        totalTime = 100;
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void challengingStrategyTest() {
        remainingTime = 80;
        assertTrue(remainingTime <= totalTime);
        alien.findStrategy(totalTime, remainingTime);
        String type = alien.getStrategy().getType();
        assertEquals("ChallengingStrategy", type);
    }

    @org.junit.Test
    public void confusedStrategyTest() {
        remainingTime = 50;
        assertTrue(remainingTime <= totalTime);
        alien.findStrategy(totalTime, remainingTime);
        String type = alien.getStrategy().getType();
        assertEquals("ConfusedStrategy", type);
    }

    @org.junit.Test
    public void limitedStrategyTest() {
        remainingTime = 20;
        assertTrue(remainingTime <= totalTime);
        alien.findStrategy(totalTime, remainingTime);
        String type = alien.getStrategy().getType();
        assertEquals("LimitedStrategy", type);
    }

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