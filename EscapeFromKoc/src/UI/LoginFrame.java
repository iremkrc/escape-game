package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Domain.Controllers.GameController;
import Domain.Controllers.PlayerController;

public class LoginFrame extends JFrame{
	
	private static JLabel usernameLabel;
	private JTextField usernameField;
	private JButton loginButton;
	protected static String username;
	protected static String password;
	private JLabel askSign;
	private JLabel signNameLabel;
	private JTextField signNameField;
	private JButton signUp;	

	private static List<String> usernameList;
	
	public LoginFrame() {
		
		super("Login Page");
		usernameList = new ArrayList<String>();
		Scanner scanner;
		try {
			scanner = new Scanner(new File("usernames.txt"));
			while (scanner.hasNext()) {
				usernameList.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

					

		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		setBounds(300, 200, 1000, 600);
		add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(465, 50, 70, 20);
		panel.add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(400, 80, 200, 28);
		panel.add(usernameField);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(455, 140, 90, 25);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(Color.BLACK);
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				username = usernameField.getText();
				
				if (usernameList.contains(username)) {
					new RunningModeFrame();
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Username does not exist");
					usernameField.setText("");
				}
				
				GameController.getInstance().setPlayer(new PlayerController());
				GameController.getInstance().timeLeft= 600*10000;
				
			}
		});
		panel.add(loginButton);
		
		askSign = new JLabel("Haven't signed up yet? Let's change that!");
		askSign.setBounds(380, 230, 240, 28);
		panel.add(askSign);
		
		signNameLabel = new JLabel("Username");
		signNameLabel.setBounds(465, 280, 70, 20);
		panel.add(signNameLabel);
		
		signNameField = new JTextField();
		signNameField.setBounds(400, 310, 200, 28);
		panel.add(signNameField);
		
		signUp = new JButton("Sign Up");
		signUp.setBounds(455, 370, 90, 25);
		signUp.setForeground(Color.WHITE);
		signUp.setBackground(Color.BLACK);
		signUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String signUpName = signNameField.getText();
				
			
				try {
					
					FileWriter writer = new FileWriter("usernames.txt", true);
					
					if(signUpName.equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter a username to sign up");
					}else if (usernameList.contains(signUpName)) {
						JOptionPane.showMessageDialog(null, "Username already exists");
					}else{
						writer.write(signUpName + "\n");
						usernameList.add(signUpName);
						JOptionPane.showMessageDialog(null, "Sign up successful");
					}
					signNameField.setText("");
					writer.close();
					
				  } catch (IOException k) {
					System.out.println("An error occurred.");
					k.printStackTrace();
				  }
			}
		});
		panel.add(signUp);	
		
		
		
		
		this.setVisible(true); // to make everything visible including the jtextfield s
		
	}

}
