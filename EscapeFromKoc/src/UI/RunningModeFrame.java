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
import Domain.Alien.TimeWastingAlien;
import Domain.Alien.TimeWastingStrategy;
import Domain.Alien.ChallengingStrategy;
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
	private static JLabel powerUpCountLabel, powerUpCountLabel1, powerUpCountLabel2, powerUpCountLabel3;
	private static JLabel LifeLabel;
	private static JButton pauseButton;
	private static JButton exitButton;
	private int second;
	private String ddSecond;
	DecimalFormat dFormat = new DecimalFormat("00");
	private int gameStatus = 0;
	private int powerupTime = 0;
    GameController game;
	PlayerController player;	
	Timer mainTimer, powerupTimer, alienTimer, countdownTimer;
	boolean timeIsRunning = false;
	private Alien alien;
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
		setBounds(300,100, 1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setLayout(new BorderLayout());
		add(mainPanel,BorderLayout.CENTER);

		///--------------------
		//east inventory panel
		JPanel inventoryPanel=new JPanel();  
        inventoryPanel.setBounds(300,600,300,200);  
        inventoryPanel.setBackground(Color.gray);  
		setResizable(false);
		inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));
		mainPanel.add(inventoryPanel,BorderLayout.EAST);

		powerUpCountLabel = new JLabel("-   Inventory Power-up List   -");
		powerUpCountLabel1 = new JLabel("Hint: " + game.getPlayer().getPlayerState().inventory.getPowerupCount("hint")); 
		powerUpCountLabel2 = new JLabel("Protection Vest: " + game.getPlayer().getPlayerState().inventory.getPowerupCount("vest"));
		powerUpCountLabel3 = new JLabel("Bottle: " + game.getPlayer().getPlayerState().inventory.getPowerupCount("bottle"));
		
		inventoryPanel.add(powerUpCountLabel);
		inventoryPanel.add(powerUpCountLabel1);
		inventoryPanel.add(powerUpCountLabel2);
		inventoryPanel.add(powerUpCountLabel3);

		//top stats panel
		JPanel statsPanel=new JPanel();  
        statsPanel.setBounds(300,600,600,600);  
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

		second = game.getGameState().getTime();
		TimeLabel = new JLabel("Time: "+ second+"s");
		TimeLabel.setBounds(500, 50, 200,20);
		statsPanel.add(TimeLabel,BorderLayout.WEST);
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
					alienTimer.stop();
					
				}else {
					System.out.println(game.isPaused());
					game.setPaused(false);
					pauseButton.setText("Pause");
					countdownTimer.start();
					countdownTimer.start();
					alienTimer.start();
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
					powerUpCountLabel1.setText("\t      Hint: " + game.getPlayer().getPlayerState().inventory.getPowerupCount("hint")); 
					powerUpCountLabel2.setText("\t      Protection Vest: " + game.getPlayer().getPlayerState().inventory.getPowerupCount("vest"));
					powerUpCountLabel3.setText("\t      Bottle: " + game.getPlayer().getPlayerState().inventory.getPowerupCount("bottle"));
					int time = game.getGameState().getTime();
					if(second != time){
						second = time;
						TimeLabel.setText("Time: "+ second+"s");
					}
				}else {
					if(gameStatus==0) {
						gameStatus=1;
						new EndGameFrame();
						dispose();
					}
				}
			}
		};
		
		ActionListener timeWastingListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!game.isPaused()) {
					if(alien != null && !alien.isEmpty()){
						if(alien.getType() == "TimeWasting"){
							TimeWastingStrategy s = ((TimeWastingAlien) alien).findStrategy(game.getGameState().getTotalTime(), game.getGameState().getTime());
							TimeWastingStrategy currentStrategy = ((TimeWastingAlien) alien).getStrategy();
							if(s.getType() != currentStrategy.getType()){
								if(currentStrategy.getType() == "ChallengingStrategy"){
									((ChallengingStrategy) currentStrategy).stopTimer();
								}
								((TimeWastingAlien) alien).setStrategy(s);
								System.out.println("Strategy changed from " + currentStrategy.getType() + " to "  + s.getType());
								alien.action();
							}
							
						}
					}
				}
			}

		};

		ActionListener alienListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!game.isPaused()) {
					if(alien != null){
						if(alien.getType() == "TimeWasting"){
							if(((TimeWastingAlien) alien).getStrategy().getType() == "ChallengingStrategy"){
								((ChallengingStrategy) ((TimeWastingAlien) alien).getStrategy()).stopTimer();
							}
						}
					}
					alien = game.getAlienController().createAlienRandomly();
					if(alien.getType() == "TimeWasting"){
						TimeWastingStrategy s = ((TimeWastingAlien) alien).findStrategy(game.getGameState().getTotalTime(), game.getGameState().getTime());
						((TimeWastingAlien) alien).setStrategy(s);
					}
	
					game.getAlienController().setAlien(alien);
					alien.action();
					System.out.println("time left: " + game.getGameState().getTime() + " seconds");
				}
			}
		};

		//powerup timer tick
		ActionListener powerupListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!game.isPaused()) {
					powerupTime ++;
					if(powerupTime % 2 == 0){
						powerupTime = 0;
						IPowerup powerup = game.getPowerupController().createPowerupRandomly();
						game.getPowerupController().setPowerup(powerup);
					}else{
						game.getPowerupController().setPowerup(null);
					}
				}
			}
		};
		
		Timer timer = new Timer(clockMiliSeconds, tickListener);
		timer.start();
		timer.getDelay();

		Timer alienTimer = new Timer(10000, alienListener);
		alienTimer.start();

		Timer timeWastingTimer = new Timer(1000, timeWastingListener);
		timeWastingTimer.start();


		Timer powerupTimer = new Timer(12000, powerupListener);
		powerupTimer.start();
		
		GameKeyListener listeners = new GameKeyListener(game);
		addKeyListener(listeners);
		
		ActionListener healthListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int healthControl = game.getPlayer().getPlayerState().getHealth();
				LifeLabel.setText("Life: "+ player.getPlayerState().getHealth());
				
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
	}
	
	public void countdownTimer(){
		countdownTimer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!game.isPaused()){
					second = game.getGameState().getTime() - 1;
					game.getGameState().setTime(second);
					ddSecond = dFormat.format(second);
					TimeLabel.setText("Time: "+ ddSecond+"s");
					if(game.getGameState().getTime()==0){
						countdownTimer.stop();
						game.getGameState().setIsOver(true);
					}
				}
			}
		});
	}
}
