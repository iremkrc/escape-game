package UI;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;

import Domain.Controllers.GameController;


public class BuildingModeFrame extends JFrame{
	private static final String BACKGROUND_IMAGE_ADDRESS = "src/images/background.png";
	private static JLabel BuildingLabel;
	private static JLabel ObjectsLeftLabel;
	private static JLabel TotalObjectLabel;
	private static JButton passNextButton;
	private static JButton helpButton;
	private int lastBuildingIndex;
	public int clockMiliSeconds;
	private boolean buildingModeDone = false;

    GameController game;
    
    @SuppressWarnings("deprecation")
	public BuildingModeFrame() {
		super("Building Mode");
		
		
		game = GameController.getInstance();
		lastBuildingIndex = game.getBuildingCount();
		clockMiliSeconds = 5;
		
		//initialize frame
		setBounds(100,100, 2400, 1200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);
	
		BuildingLabel = new JLabel("Current Building: " + game.currentBuilding.getBuildingName());
		BuildingLabel.setBounds(465, 50, 70, 20);
		mainPanel.add(BuildingLabel);
		
		TotalObjectLabel = new JLabel("Needed Object Amount: " + game.currentBuilding.getIntendedObjectCount());
		TotalObjectLabel.setBounds(465, 50, 70, 20);
		mainPanel.add(TotalObjectLabel);
				
		ObjectsLeftLabel = new JLabel("Current Object Amount: " + game.currentBuilding.getCurrentObjectCount());
		ObjectsLeftLabel.setBounds(465, 50, 70, 20);
		mainPanel.add(ObjectsLeftLabel);
		
		passNextButton = new JButton("Pass to Next Building");
		passNextButton.setBounds(455, 140, 90, 25);
		passNextButton.setForeground(Color.WHITE);
		passNextButton.setBackground(Color.BLACK);
		passNextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				if(game.getCurrentBuildingIndex() < lastBuildingIndex - 1) {
					game.setCurrentBuilding(game.getCurrentBuildingIndex() + 1);
					BuildingLabel.setText("Current Building: " + game.currentBuilding.getBuildingName());
					TotalObjectLabel.setText("Needed Object Amount: " + game.currentBuilding.getIntendedObjectCount());
					ObjectsLeftLabel.setText("Current Object Amount: " + game.currentBuilding.getCurrentObjectCount());
					if(game.getCurrentBuildingIndex() == lastBuildingIndex - 1) {
						passNextButton.setText("Start Running Mode");
					}
					passNextButton.setEnabled(false);
				}else {
					if(game.getCurrentBuildingIndex() == lastBuildingIndex - 1) {
						buildingModeDone = true;
					}
					game.initializeRunningMode();
					game.setCurrentBuilding(0);
				   	System.out.println("I am about to RunningModeFrame...");
					new RunningModeFrame();
				   	System.out.println("I am about to saveGame...");
					//game.saveGame(); // a save for usernames to json file when game starts.
					dispose();
				}
			}
		});
		passNextButton.setFocusable(false);
		mainPanel.add(passNextButton);
		passNextButton.setEnabled(false);

		//help button

		helpButton = new JButton("Help");
		helpButton.setBounds(455, 140, 90, 25);
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("help button");	
				String menuMessage = "The player walks around using the arrow keys. \nHe/she can go to the east, west, north and south but cannot pass through walls.\nHe/she can only open the exit door of a building if he/she finds the key. \nThe game starts from the Student Center. \nFinding the keys one by one, the player's aim is to travel to these buildings in the given order: \nCASE building, SOS building, SCI building, ENG building and SNA building.\nOnce the player finds the exit key from the SNA building, the game ends and the player wins. \nTo find the keys, the player uses a left click on the objects with the mouse. \nIf the key is there, it appears for a second and then, the door is opened. \nTo click the objects, the player should be next to the objects. \nPlayer has a bag to collect the power ups and keep them for later use. \nPlayer can collect powerups. \nPowerups include protection vest powerup, life powerup, extra life powerup, plastic bottle powerup and hint powerup. \nThere are 3 types of aliens: blind alien, shooter alien and time wasting alien. \nBlind alien, which is represented by pink color, cannot see the player. He randomly walks around. However, this alien is sensitive to the voices. \nWhen the player has the plastic bottle power-up, if she/he throws the bottle, he/she can fool the alien.\nTime wasting alien, which is represented by green color, does not kill the player but it changes the location of the key randomly every 5 seconds. \nShooter alien, which is represented by blue color, appears in a random location in the building and shoots a bullet every second. \nIf the player is close to the shooter alien less than 4 squares, then he/she will lose a life. \nAlso, if the player wears a protection vest, then he/she can get close to the shooter alien without losing a life.";						
				UIManager.put("OptionPane.minimumSize",new Dimension(500,500)); 
				JOptionPane.showMessageDialog(null, menuMessage, "HOW TO PLAY?", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mainPanel.add(helpButton);
		helpButton.setFocusable(false);
		
		//add game panel
		final BuildingLayoutPanel buildPanel = new BuildingLayoutPanel("Building Mode");
		buildPanel.setOpaque(false);
		mainPanel.add(buildPanel);
		
		setVisible(true); 
		pack();

		//timer tick
		ActionListener tickListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!buildingModeDone) {
					buildPanel.repaint();
					ObjectsLeftLabel.setText("Current Object Amount: " + game.currentBuilding.getCurrentObjectCount());
					if(game.currentBuilding.getCurrentObjectCount() == game.currentBuilding.getIntendedObjectCount()) {
						passNextButton.setEnabled(true);
					}else {
						passNextButton.setEnabled(false);
					}
				}
			}
		};
		
		Timer timer = new Timer(clockMiliSeconds, tickListener);
		timer.start();
		
		GameKeyListener listeners = new GameKeyListener(game);
		addKeyListener(listeners);
	}
}