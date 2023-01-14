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
import Domain.Controllers.LoginController;
import Domain.Controllers.PlayerController;

public class LoginFrame extends JFrame{
	
	private static JLabel userNameLabel;
	private JTextField userNameField;
	private JButton loginButton;
	protected static String userName;
	protected static String password;
	private JLabel askSign;
	private JLabel signNameLabel;
	private JTextField signNameField;
	private JButton signUp;	
	
	private LoginController loginController;
	private GameController game;
	
	public LoginFrame() {
		
		super("Login Page");
		this.loginController = new LoginController(GameController.getInstance());
		this.game = GameController.getInstance();
		
					

		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		setBounds(300, 200, 1000, 600);
		add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		userNameLabel = new JLabel("Username");
		userNameLabel.setBounds(465, 50, 70, 20);
		panel.add(userNameLabel);
		
		userNameField = new JTextField();
		userNameField.setBounds(400, 80, 200, 28);
		panel.add(userNameField);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(455, 140, 90, 25);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(Color.BLACK);
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userName = userNameField.getText();
				
				if (loginController.isRegistered(userName)) {
					loginController.setLoginName(userName);

					GameTypeFrame chooseLoadingOption = new GameTypeFrame();
					chooseLoadingOption.setVisible(true);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Username does not exist");
					userNameField.setText("");
				}
				
				GameController.getInstance().setPlayer(new PlayerController());
				//GameController.getInstance().setTimeLeft(600*10000);
				
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
				int signUpCases = loginController.signUpUser(signUpName);
				
				if(signUpCases == 0) {
					JOptionPane.showMessageDialog(null, "Please enter a username to sign up");
				}else if (signUpCases == 1) {
					JOptionPane.showMessageDialog(null, "Username already exists");
				}else if(signUpCases == 2){
					JOptionPane.showMessageDialog(null, "Sign up successful");
				}else if(signUpCases == 3){
					JOptionPane.showMessageDialog(null, "Sign up failed");
				}
				signNameField.setText("");
			}
		});
		panel.add(signUp);	
		
		
		
		
		this.setVisible(true); // to make everything visible including the jtextfield s
		
	}

}
