package UI;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

import Domain.Controller.GameController;

public class GameKeyListener implements KeyListener, MouseListener{
    GameController escapeFromKocGame;
    int lastKey = -1;
    Scanner scanner = new Scanner(System.in);
    public GameKeyListener(GameController escapeFromKocGame) {
        this.escapeFromKocGame=escapeFromKocGame;
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        if(arg0.getKeyCode()==KeyEvent.VK_P) escapeFromKocGame.setPaused(true);

        else if(arg0.getKeyCode()==KeyEvent.VK_R) escapeFromKocGame.setPaused(false);

        else if(arg0.getKeyCode()==KeyEvent.VK_B) escapeFromKocGame.setPaused(true);

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
    }
    
    @Override
    public void keyReleased(KeyEvent arg0) {
    	if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) System.out.println("hahahahah");;
    }
    
    @Override
    public void keyTyped(KeyEvent arg0) {

    }

	@Override
	public void mouseClicked(MouseEvent e) {
		escapeFromKocGame.pickKey(e.getX(),e.getY());	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}