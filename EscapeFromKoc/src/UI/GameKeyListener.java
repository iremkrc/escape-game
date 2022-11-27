package UI;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import Domain.GameState;

public class GameKeyListener implements KeyListener{
    GameState escapeFromKocGame;
    int lastKey = -1;
    Scanner scanner = new Scanner(System.in);
    public GameKeyListener(GameState escapeFromKocGame) {
        this.escapeFromKocGame=escapeFromKocGame;
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        if(arg0.getKeyCode()==KeyEvent.VK_P) escapeFromKocGame.setPaused(true);

        else if(arg0.getKeyCode()==KeyEvent.VK_R) escapeFromKocGame.setPaused(false);

        else if(arg0.getKeyCode()==KeyEvent.VK_B) {
            escapeFromKocGame.setPaused(true);
        }

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
            else if(arg0.getKeyCode()==KeyEvent.VK_C) escapeFromKocGame.pickKey();

        }
    }
    
    @Override
    public void keyReleased(KeyEvent arg0) {

    }
    
    @Override
    public void keyTyped(KeyEvent arg0) {

    }

}