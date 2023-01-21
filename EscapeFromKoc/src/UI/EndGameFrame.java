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

import Domain.Controllers.GameController;


public class EndGameFrame extends JFrame{
    private static final Color BACKGROUND_COLOR = new Color(255, 0, 0);
    private static final long serialVersionUID = 1L;
    GameController game = GameController.getInstance();

    public EndGameFrame() {
        super("EndGame");
        setBounds(400, 200, 800, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.black);
        JPanel mainPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        initializeMainPanel(mainPanel);
        initializeTopPanel(mainPanel, topPanel);
        initializeBottomPanel(mainPanel, bottomPanel);
        setVisible(true);
        
        game.deleteGame();
    }

    private void initializeMainPanel(JPanel mainPanel) {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);
    }

    private void initializeTopPanel(JPanel mainPanel, JPanel topPanel) {
        topPanel.setLayout(new GridBagLayout());
        JLabel gameOver = new JLabel("Game Over!", JLabel.CENTER);
        gameOver.setSize(1000, 200);
        gameOver.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        topPanel.add(gameOver);
        topPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.add(topPanel);
    }

    private void initializeBottomPanel(JPanel mainPanel, JPanel bottomPanel) {
        bottomPanel.setLayout(new BorderLayout());
        JLabel message = new JLabel("You could achieve the Building: "+ game.currentBuilding.getBuildingName(),JLabel.CENTER);
        JLabel message2 = new JLabel("",JLabel.CENTER);
        if(game.getGameState().isWon()) {
        	message2.setText("YOU WON THE GAME! YOU ESCAPED!");
        }else {
        	message2.setText("YOU LOST! YOU CANNOT ESCAPE!");
        }
        message.setSize(300, 200);
        message2.setSize(300, 200);
        message.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        message2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        bottomPanel.add(message, BorderLayout.CENTER);
        bottomPanel.add(message2, BorderLayout.AFTER_LAST_LINE);
        bottomPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.add(bottomPanel);
        System.out.println("Game ended");
    }

}