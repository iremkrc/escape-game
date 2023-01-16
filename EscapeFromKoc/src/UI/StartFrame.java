package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private static final Color BACKGROUND_COLOR = new Color(200, 0, 0);
	
	
	public StartFrame() {
		super("Escape From Koc Game");
		setBounds(300, 200, 1000, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		initializeMainPanel(mainPanel);
		JPanel topPanel = new JPanel();
		initializeTopPanel(mainPanel, topPanel);
		JPanel bottomPanel = new JPanel();
		initializeBottomPanel(mainPanel, bottomPanel);
		JPanel middlePanel = new JPanel(new GridBagLayout());
		initializeMiddlePanel(mainPanel, middlePanel);
		setVisible(true);	
	}
	
	public static void main(String[] args) {
		new StartFrame();
	}
	
	private void initializeMainPanel(JPanel mainPanel) {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);
	}

	private void initializeTopPanel(JPanel mainPanel, JPanel topPanel) {
		topPanel.setLayout(new BorderLayout());
		JLabel welcome = new JLabel("ESCAPE FROM KOC GAME", JLabel.CENTER);
		welcome.setSize(1000, 200);
		welcome.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
		JLabel welcome2 = new JLabel("By Group B.A.M.B.I.", JLabel.CENTER);
		welcome2.setSize(1000, 200);
		welcome2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		topPanel.add(welcome, BorderLayout.CENTER);
		topPanel.add(welcome2, BorderLayout.SOUTH);
		topPanel.setBackground(BACKGROUND_COLOR);
		mainPanel.add(topPanel);
	}

	private void initializeBottomPanel(JPanel mainPanel, JPanel bottomPanel) {
		bottomPanel.setLayout(new GridBagLayout());
		JLabel message = new JLabel("Press the button to start.");
		message.setSize(300, 200);
		message.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		bottomPanel.add(message);
		bottomPanel.setBackground(BACKGROUND_COLOR);
		mainPanel.add(bottomPanel);
	}

	private void initializeMiddlePanel(JPanel mainPanel, JPanel middlePanel) {
		JButton startButton = new JButton("Start Game");
		startButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		middlePanel.add(startButton);
		
		middlePanel.setBackground(BACKGROUND_COLOR);
		mainPanel.add(middlePanel);

		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//BuildingModeFrame buildingModeFrame = 
				new LoginFrame();
				dispose();				
			}
		});	
	}
}