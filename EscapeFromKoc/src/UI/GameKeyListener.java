package UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import Domain.Controllers.GameController;

public class GameKeyListener implements KeyListener{
    GameController escapeFromKocGame;
    int lastKey = -1;
    Scanner scanner = new Scanner(System.in);
    public GameKeyListener(GameController escapeFromKocGame) {
        this.escapeFromKocGame=escapeFromKocGame;
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
       // if(arg0.getKeyCode()==KeyEvent.VK_P) escapeFromKocGame.setPaused(true);

        //else if(arg0.getKeyCode()==KeyEvent.VK_R) escapeFromKocGame.setPaused(false);

        if((arg0.getKeyCode()==KeyEvent.VK_1||arg0.getKeyCode()==KeyEvent.VK_2||
                arg0.getKeyCode()==KeyEvent.VK_3||arg0.getKeyCode()==KeyEvent.VK_4)) {
            if(lastKey==-1) lastKey= (int) (arg0.getKeyChar()-48);
            else {
                int first = lastKey;
                int second = (int) (arg0.getKeyChar()-48);
                lastKey=-1;
                escapeFromKocGame.setPaused(false);
            }
        }

        if(!escapeFromKocGame.isPaused()) {
            if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) escapeFromKocGame.moveAvatar("right");
            else if(arg0.getKeyCode()==KeyEvent.VK_LEFT) escapeFromKocGame.moveAvatar("left");
            else if(arg0.getKeyCode()==KeyEvent.VK_UP) escapeFromKocGame.moveAvatar("up");
            else if(arg0.getKeyCode()==KeyEvent.VK_DOWN) escapeFromKocGame.moveAvatar("down");
        }

        if(arg0.getKeyCode()==KeyEvent.VK_H){
            try {
                escapeFromKocGame.activatePowerUp("hint");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(arg0.getKeyCode()==KeyEvent.VK_B){
            try {
                escapeFromKocGame.activatePowerUp("bottle");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(arg0.getKeyCode()==KeyEvent.VK_V){
            try {
                escapeFromKocGame.activatePowerUp("vest");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if(arg0.getKeyCode()==KeyEvent.VK_A){
        	if(escapeFromKocGame.getGameState().isBottleDirectionSettable()) {
        		escapeFromKocGame.setBottlePowerupDirection("West");
        		escapeFromKocGame.getGameState().setBottleDirectionSettable(false);
        	}
        }
        else if(arg0.getKeyCode()==KeyEvent.VK_D){
        	if(escapeFromKocGame.getGameState().isBottleDirectionSettable()) {
        		escapeFromKocGame.setBottlePowerupDirection("East");
        		escapeFromKocGame.getGameState().setBottleDirectionSettable(false);
        	}
        }
        else if(arg0.getKeyCode()==KeyEvent.VK_W){
        	if(escapeFromKocGame.getGameState().isBottleDirectionSettable()) {
        		escapeFromKocGame.setBottlePowerupDirection("North");
        		escapeFromKocGame.getGameState().setBottleDirectionSettable(false);
        	}
        }
        else if(arg0.getKeyCode()==KeyEvent.VK_X){
        	if(escapeFromKocGame.getGameState().isBottleDirectionSettable()) {
        		escapeFromKocGame.setBottlePowerupDirection("South");
        		escapeFromKocGame.getGameState().setBottleDirectionSettable(false);
        	}
        }
    }
    
    @Override
    public void keyReleased(KeyEvent arg0) {
    	if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) System.out.println("hahahahah");;
    }
    
    @Override
    public void keyTyped(KeyEvent arg0) {

    }
}