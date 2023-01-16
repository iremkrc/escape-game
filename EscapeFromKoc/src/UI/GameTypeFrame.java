package UI;

import Domain.Controllers.GameController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GameTypeFrame extends JFrame {
    private JButton startNewGameButton, resumeGameButton;
    private JPanel mainPanel;
    private GameController game;
    public GameTypeFrame() {
        super("Game Mode Selection");
        this.game = GameController.getInstance();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setBounds(300, 200, 1000, 600);
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        startNewGameButton = new JButton(" \tStart New Game\t ");

        // set size
        startNewGameButton.setSize(120,120);


        // setBackground
        startNewGameButton.setBackground(new Color(241,170,106));

        // Set border
        startNewGameButton.setBorder(new LineBorder(new Color(241,170,106), 2, true));

        // Set font
        startNewGameButton.setFont(new Font("Open Sans", Font.BOLD, 24));

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

        resumeGameButton = new JButton("\tResume Game \t\t");

        // set size
        resumeGameButton.setSize(120,120);


        // setBackground
        resumeGameButton.setBackground(new Color(241,170,106));

        // Set border
        resumeGameButton.setBorder(new LineBorder(new Color(241,170,106), 2, true));
        resumeGameButton.setBorderPainted(true);

        // Set font
        resumeGameButton.setFont(new Font("Open Sans", Font.BOLD, 24));

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
                        game.setBuildingModeDone(true);
                        new RunningModeFrame();
                        dispose();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                } else if (choice == 1) {
                    try {
                        game.loadGame(1);
                        game.setBuildingModeDone(true);
                        new RunningModeFrame();
                        dispose();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
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
        add(mainPanel);
    }


}