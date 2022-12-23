package UI;

import java.awt.*;  
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
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import Domain.GameObjects.Powerups.IPowerup;
import Domain.GameObjects.GameObject;
import Domain.Alien.Alien;
import Domain.Controllers.AlienController;
import Domain.Controllers.GameController;
import Domain.Controllers.PowerupController;
import Domain.Controllers.PlayerController;
import Domain.Game.GameKeyListener;



public class RunningModeFrame extends JFrame{
	private static final String BACKGROUND_IMAGE_ADDRESS = "";
	private static final long serialVersionUID = 1L;
	public int clockMiliSeconds;
	private static JLabel BuildingLabel;
	private static JLabel ScoreLabel;
	private static JLabel TimeLabel;
	private static JLabel LifeLabel;
	private static JButton pauseButton;
	private static JButton exitButton;
	private int second, minute;
	private String ddSecond, ddMinute;
	DecimalFormat dFormat = new DecimalFormat("00");
	private int gameStatus = 0;
    GameController game;
	PlayerController player;	
	Timer mainTimer;
	Timer countdownTimer;
	boolean timeIsRunning = false;
	//private boolean isHealthDone = false;
    
    @SuppressWarnings("deprecation")
    public RunningModeFrame() {
		super("Running Mode");
		
		setLayout(new BorderLayout());
		game = GameController.getInstance();
		player = new PlayerController();
		game.setPlayer(player);
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

		///--------------------
		//top stats panel
		JPanel statsPanel=new JPanel();  
        statsPanel.setBounds(300,600,1000,600);  
        statsPanel.setBackground(Color.gray);  
		setResizable(false);
		statsPanel.setLayout(new GridLayout(1,3));
		add(statsPanel,BorderLayout.NORTH);

		BuildingLabel = new JLabel("Building: "+ game.currentBuilding.getBuildingName());
		BuildingLabel.setBounds(465, 50, 200, 20);
		statsPanel.add(BuildingLabel,BorderLayout.EAST);

		LifeLabel = new JLabel("Life: "+ player.getPlayerState().getHealth());
		LifeLabel.setBounds(600, 50, 200, 20);
		statsPanel.add(LifeLabel,BorderLayout.CENTER);

		TimeLabel = new JLabel("");
		TimeLabel.setBounds(500, 50, 200,20);
		statsPanel.add(TimeLabel,BorderLayout.WEST);

		second = 25;
		minute = 0;
		countdownTimer();
		countdownTimer.start();

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
					countdownTimer.stop();
					
				}else {
					System.out.println(game.isPaused());
					game.setPaused(false);
					pauseButton.setText("Pause");
					countdownTimer.start();
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
				countdownTimer.stop();
				if (JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dispose();
					
				} else {
					game.setPaused(false);
					countdownTimer.start();
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
					LifeLabel.setText("Life: "+ player.getPlayerState().getHealth());
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
				if(!game.isPaused()) {
					Alien alien = game.getAlienController().createAlienRandomly();
					game.getAlienController().setAlien(alien);
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
		timer.getDelay();

		Timer alienTimer = new Timer(10000, alienListener);
		alienTimer.start();

		Timer powerupTimer = new Timer(12000, powerupListener);
		powerupTimer.start();
		
		GameKeyListener listeners = new GameKeyListener(game);
		addKeyListener(listeners);
		
		ActionListener healthListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int healthControl = game.getPlayer().getPlayerState().getHealth();
				
				if(healthControl <= 0) game.getGameState().setIsOver(true);
				
				if(game.getGameState().isOver()) {
					game.setPaused(true);
					countdownTimer.stop();
					//isHealthDone = true;
					dispose();
					
				}	
			}
		};
		
		Timer healthTimer = new Timer(10, healthListener);
		healthTimer.start();
		
		//if(isHealthDone) healthTimer.stop();
		
		//////
		ActionListener ctimerListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int currentBuildingIndex = game.getGameState().getCurrentBuildingIndex();
	
				if(game.getGameState().isKeyFound()) {
					countdownTimer.stop();
					if(currentBuildingIndex == 1){
						game.getGameState().setKeyFound(false);
						second = 35;
						minute = 0;
					}
					if(currentBuildingIndex == 2){
						game.getGameState().setKeyFound(false);
						second = 50;
						minute = 0;
					}
					if(currentBuildingIndex == 3){
						game.getGameState().setKeyFound(false);
						second = 10;
						minute = 1;
					}
					if(currentBuildingIndex == 4){
						game.getGameState().setKeyFound(false);
						second = 35;
						minute = 1;
					}
					if(currentBuildingIndex == 5){
						game.getGameState().setKeyFound(false);
						second = 5;
						minute = 2;
					}
					countdownTimer.start();
				}
				
				if(game.getGameState().isOver()) {
					game.setPaused(true);
					countdownTimer.stop();
					dispose();	
				}
				
			}
		};	
		Timer cTimer = new Timer(5, ctimerListener);
		cTimer.start();
		
	}
	
	public void countdownTimer(){
		countdownTimer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				second--;
				ddSecond = dFormat.format(second);
				ddMinute = dFormat.format(minute);
				TimeLabel.setText("Time: " + ddMinute + ":"+ ddSecond);
			
				if(second == -1){
					second = 59;
					minute--;
					ddSecond = dFormat.format(second);
					ddMinute = dFormat.format(minute);
					TimeLabel.setText("Time: " + ddMinute + ":"+ ddSecond);

				}
				if(minute==0 && second==0){
					countdownTimer.stop();
					game.getGameState().setIsOver(true);
				}
			}
		});
	}

}
