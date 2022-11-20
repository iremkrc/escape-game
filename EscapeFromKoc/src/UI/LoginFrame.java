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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	
	public LoginFrame() {
		
		super("Login Page");
		
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
				username = usernameField.getText();//

				/*if (User.usernameList.contains(userName)) {
					JOptionPane.showMessageDialog(null, "Login Successful");
				}
				else {
					JOptionPane.showMessageDialog(null, "Username or Password mismatch ");
				}*/
				new BuildingModeFrame("xx");
				dispose();
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
				
				/*if(signUpName.length() > 0) {
					
					if(!(User.usernameList.contains(signUpNickname))) {
						
						JOptionPane.showMessageDialog(null, "Welcome on board");
						User newUser = new User(signUpName);
						LoginPage frame = new LoginPage();
						
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Ooops! It looks like this user already exists.");
					}								
				}
				else {
					JOptionPane.showMessageDialog(null, "Ooops! It looks like some info is missing or invalid.");
				}*/
				new BuildingModeFrame("xx");
				dispose();
			}
		});
		panel.add(signUp);	
		
		
		
		
		this.setVisible(true); // to make everything visible including the jtextfield s
		
	}

}
