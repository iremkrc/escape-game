package UI;

import Domain.Controllers.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GameTypeFrame extends JFrame {
    private JButton startNewGameButton, resumeGameButton;
    private JPanel mainPanel;
    private GameController game;
    private static final Color BACKGROUND_COLOR = new Color(255, 216, 169);
    public GameTypeFrame() {
        super("Game Mode Selection");
        this.game = GameController.getInstance();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setBounds(300, 200, 1000, 600);
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBackground(BACKGROUND_COLOR);
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.getContentPane().setBackground(BACKGROUND_COLOR);




        startNewGameButton = new JButton("Start New Game");
        // set size
        startNewGameButton.setSize(120,120);
        startNewGameButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        // Add mouse listener to button
        startNewGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startNewGameButton.setBackground(new Color(132,177,218,255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                startNewGameButton.setBackground(new Color(241,170,106));
            }
        });

        startNewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // start new game
                new BuildingModeFrame();
                dispose();
                //JOptionPane.showMessageDialog(null, "Starting new game...");
            }
        });

        resumeGameButton = new JButton(" Resume Game  ");
        // set size
        resumeGameButton.setSize(120,120);
        resumeGameButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        // Add mouse listener to button
        resumeGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                resumeGameButton.setBackground(new Color(132,177,218,255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                resumeGameButton.setBackground(new Color(241,170,106));
            }
        });
        resumeGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Load From Local", "Load From Database"};
                int choice = JOptionPane.showOptionDialog(null, "Please select an option to resume:", "Resume Game",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (choice == 0) {
                    try {
                        game.loadGame(0);
                        new RunningModeFrame();
                        dispose();
                    } catch (FileNotFoundException ex) {
                    	new BuildingModeFrame();
                        dispose();
                        //throw new RuntimeException(ex);
                    }

                } else if (choice == 1) {
                    try {
                        game.loadGame(1);
                        new RunningModeFrame();
                        dispose();
                    } catch (Exception ex) {
                    	new BuildingModeFrame();
                        dispose();
                        //throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid choice. Exiting...");
                    System.exit(0);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 15, 15, 15); // Space between buttons
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(startNewGameButton, gbc);
        mainPanel.add(Box.createRigidArea(new Dimension(5, 50))); // Space between buttons

        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(resumeGameButton,gbc);
        mainPanel.setBackground(BACKGROUND_COLOR);
        add(mainPanel);
    }
}