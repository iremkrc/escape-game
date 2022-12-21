package Domain.Alien;

import java.awt.Graphics;

import Domain.Game.Location;

import java.awt.Color;

// If the remaining time is between 30% - 70%, it will get confused and indecisive.
// So it will stay in the place in which it appears, then disappear after 2 seconds without doing anything.
public class ConfusedStrategy implements TimeWastingStrategy {

    private TimeWastingAlien alien;
    private Graphics g;

    @Override
    public void wasteTime() {
        // TODO Auto-generated method stub
        System.out.println("1-ConfusedStrategy");
        new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    // your code here
                    System.out.println("3-ConfusedStrategy");
                    alien.setEmpty(true);
                    //g.setColor(Color.WHITE);
                    //g.fillOval((int)loc.getXLocation(), (int)loc.getYLocation(), alien.getWidth(), alien.getHeight());
                    //g.clearRect(xLocation, yLocation, xLocation + alien.getWidth(), yLocation + alien.getHeight());
                    //g.fillOval((int)loc.getXLocation(), (int)loc.getYLocation(), width, height);
                }
            }, 
            2000 
        );

    }

    public TimeWastingAlien getAlien() {
        return this.alien;
    }

    public void setAlien(TimeWastingAlien alien) {
        this.alien = alien;
    }

    public Graphics getG() {
        return this.g;
    }

    public void setG(Graphics g) {
        this.g = g;
    }
    
    
}
