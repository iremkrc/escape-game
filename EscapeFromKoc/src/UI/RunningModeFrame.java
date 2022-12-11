package UI;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Domain.GameObjects.GameObject;
import Domain.GameObjects.Powerups.IPowerup;
import Domain.Alien.Alien;
import Domain.Controllers.AlienController;
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
	private static JLabel BuildingLabel;
	private static JButton pauseButton;
	private static JButton exitButton;
	Timer mainTimer;

    
    @SuppressWarnings("deprecation")
    public RunningModeFrame() {
		super("Running Mode");
		
		setLayout(new BorderLayout());
		game = GameController.getInstance();
		game.setPlayer(new PlayerController());
		game.setPowerupController(new PowerupController());
		game.setAlienController(new AlienController());
		clockMiliSeconds = 10;	
		
		//initialize frame
		setBounds(300,200, (4*1000)/3, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setLayout(new BorderLayout());
		add(mainPanel,BorderLayout.CENTER);
		
		BuildingLabel = new JLabel("Current Building: " + game.currentBuilding.getBuildingName());
		BuildingLabel.setBounds(465, 50, 70, 20);
		mainPanel.add(BuildingLabel,BorderLayout.NORTH);
		//-----------------------------------------------------------------
		// BUTTON PART
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		pauseButton = new JButton("Pause");
		pauseButton.setBounds(455, 140, 90, 25);
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!game.isPaused()) {
					System.out.println(game.isPaused());
					game.setPaused(true);
					pauseButton.setText("Resume");
				}else {
					System.out.println(game.isPaused());
					game.setPaused(false);
					pauseButton.setText("Pause");
				}
			}
		});
		buttonPanel.add(pauseButton);
		pauseButton.setFocusable(false);
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(455, 140, 90, 25);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setPaused(true);
				if (JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dispose();
				} else {
					game.setPaused(false);
				}
			}
		});
		buttonPanel.add(exitButton);
		exitButton.setFocusable(false);
		
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		//-----------------------------------------------------------------
		//add game panel
		final LayoutPanel gamePanel = new LayoutPanel("Running Mode");
		gamePanel.setOpaque(false);
		mainPanel.add(gamePanel, BorderLayout.CENTER);
		
		setVisible(true); 
		pack();

		//timer tick
		ActionListener tickListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!game.isOver()) {
					gamePanel.repaint();
					BuildingLabel.setText("Current Building: " + game.currentBuilding.getBuildingName());
				}else {
					if(gameStatus==0) {
						gameStatus=1;
						new EndGameFrame();
						dispose();
					}
				}
			}

		};
		ActionListener alienListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!game.isOver()) {
					Alien alien = game.getAlienController().createAlienRandomly();
					game.getAlienController().setAlien(alien);
				}else {
						dispose();
				}
			}
		};
		
		//powerup timer tick
		ActionListener powerupListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
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

		timer.getDelay();

		Timer alienTimer = new Timer(10000, alienListener);
		alienTimer.start();
		
		GameKeyListener listeners = new GameKeyListener(game);
		addKeyListener(listeners);
	}
}