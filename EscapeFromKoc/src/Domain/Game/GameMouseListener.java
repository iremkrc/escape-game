package Domain.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

import Domain.Controllers.GameController;

public class GameMouseListener implements MouseListener{
    GameController escapeFromKocGame;
    int lastKey = -1;
    Scanner scanner = new Scanner(System.in);
    public GameMouseListener(GameController escapeFromKocGame) {
        this.escapeFromKocGame=escapeFromKocGame;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
    	System.out.println("mouse pressed");
		if(escapeFromKocGame.isBuildingModeDone()) {
			escapeFromKocGame.pickKey(e.getX(),e.getY());	
		}else {
			escapeFromKocGame.addObjectToCurrentBuilding(e.getX(),e.getY());
		}
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