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

import Domain.Controllers.GameController;
import Domain.Game.GameKeyListener;

public class BuildingModeFrame extends JFrame{
	private static final String BACKGROUND_IMAGE_ADDRESS = "src/images/background.png";
	private static JLabel BuildingLabel;
	private static JLabel ObjectsLeftLabel;
	private static JLabel TotalObjectLabel;
	private static JButton passNextButton;
	private int lastBuildingIndex;
	public int clockMiliSeconds;
	private int gameStatus = 0;
    GameController game;
    
    @SuppressWarnings("deprecation")
	public BuildingModeFrame() {
		super("Running Mode");
		
		setLayout(new BorderLayout());
		game = GameController.getInstance();
		lastBuildingIndex = game.buildingCount;
		clockMiliSeconds = 5;
		
		//initialize frame
		setBounds(300,200, (4*1000)/3, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

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
				if(game.currentBuildingIndex < lastBuildingIndex - 1) {
					game.setCurrentBuilding(game.currentBuildingIndex + 1);
					BuildingLabel.setText("Current Building: " + game.currentBuilding.getBuildingName());
					TotalObjectLabel.setText("Needed Object Amount: " + game.currentBuilding.getIntendedObjectCount());
					ObjectsLeftLabel.setText("Current Object Amount: " + game.currentBuilding.getCurrentObjectCount());
					if(game.currentBuildingIndex == lastBuildingIndex - 1) {
						passNextButton.setText("Start Running Mode");
					}
					passNextButton.setEnabled(false);
				}else {
					game.initializeRunningMode();
					game.setCurrentBuilding(0);
					new RunningModeFrame();
					dispose();
				}
			}
		});
		passNextButton.setFocusable(false);
		mainPanel.add(passNextButton);
		passNextButton.setEnabled(false);
		
		
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
				if(!game.isBuildingModeDone()) {
					buildPanel.repaint();
					ObjectsLeftLabel.setText("Current Object Amount: " + game.currentBuilding.getCurrentObjectCount());
					if(game.currentBuilding.getCurrentObjectCount() == game.currentBuilding.getIntendedObjectCount()) {
						passNextButton.setEnabled(true);
						if(game.currentBuildingIndex == lastBuildingIndex - 1) {
							game.setBuildingModeDone(true);
						}
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