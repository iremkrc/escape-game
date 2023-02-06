package UI;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Domain.Controllers.GameController;


public class BuildingModeFrame extends JFrame{
	private static JLabel BuildingLabel;
	private static JLabel ObjectsLeftLabel;
	private static JLabel TotalObjectLabel;
	private static JButton passNextButton;
	private static JButton helpButton;
	private int lastBuildingIndex;
	public int clockMiliSeconds;
	private boolean buildingModeDone = false;
	private static final Color BACKGROUND_COLOR = new Color(255, 255, 255);

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

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);

		BuildingLabel = new JLabel("Current Building: " + game.currentBuilding.getBuildingName());
		BuildingLabel.setBounds(465, 50, 70, 20);
		BuildingLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

		mainPanel.add(BuildingLabel);
		mainPanel.setOpaque(true);

		TotalObjectLabel = new JLabel("Needed Object Amount: " + game.currentBuilding.getIntendedObjectCount());
		TotalObjectLabel.setBounds(465, 50, 70, 20);
		TotalObjectLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		mainPanel.add(TotalObjectLabel);

		ObjectsLeftLabel = new JLabel("Current Object Amount: " + game.currentBuilding.getCurrentObjectCount());
		ObjectsLeftLabel.setBounds(465, 50, 70, 20);
		ObjectsLeftLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		mainPanel.add(ObjectsLeftLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		passNextButton = new JButton("Pass to Next Building");
		passNextButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		passNextButton.setBounds(455, 140, 90, 25);
		mainPanel.setBackground(BACKGROUND_COLOR);
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

		//add game panel
		final BuildingLayoutPanel buildPanel = new BuildingLayoutPanel("Building Mode");
		buildPanel.setOpaque(false);
		buildPanel.setBackground(BACKGROUND_COLOR);
		mainPanel.add(buildPanel);

		// Change Background Color
		mainPanel.setBackground(BACKGROUND_COLOR);
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