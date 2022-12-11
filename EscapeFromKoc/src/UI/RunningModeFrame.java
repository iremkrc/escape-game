package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import Domain.GameObjects.GameObject;
import Domain.GameObjects.Powerups.IPowerup;
import Domain.Controllers.GameController;
import Domain.Controllers.PlayerController;
import Domain.Controllers.PowerupController;
import Domain.Game.GameKeyListener;



public class RunningModeFrame extends JFrame{
	private static final String BACKGROUND_IMAGE_ADDRESS = "";
	private static final long serialVersionUID = 1L;
	public int clockMiliSeconds;
	private int gameStatus = 0;
    GameController game;
	private int count = 0;	
    
    public RunningModeFrame() {
		super("Running Mode");

		game = GameController.getInstance();
		game.setPlayer(new PlayerController());
		game.setPowerupController(new PowerupController());
		clockMiliSeconds = 10;	
		
		//initialize frame
		setBounds(300,200, (4*1000)/3, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);


		//add game panel
		final LayoutPanel gamePanel = new LayoutPanel("Running Mode");
		gamePanel.setOpaque(false);
		add(gamePanel);
		

		setVisible(true); 
		pack();

		//timer tick
		ActionListener tickListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!game.isOver()) {
					gamePanel.repaint();
				}else {
					if(gameStatus==0) {
						gameStatus=1;
						dispose();
					}
				}
			}
		};
		
		//powerup timer tick
		ActionListener powerupListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				count++;
				if(!game.isPaused()) {
					IPowerup powerup = game.getPowerupController().createPowerupRandomly();
					game.getPowerupController().setPowerup(powerup);	
				}
			}
		};
		Timer timer = new Timer(clockMiliSeconds, tickListener);
		timer.start();

		Timer powerupTimer = new Timer(12000, powerupListener);
		powerupTimer.start();
		
		GameKeyListener listeners = new GameKeyListener(game);
		addKeyListener(listeners);
	}
}