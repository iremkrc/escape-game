package UI;
import java.awt.*;

import javax.swing.*;

import Domain.Controllers.GameController;


public class EndGameFrame extends JFrame{
    private static final Color BACKGROUND_COLOR = new Color(255, 216, 169);
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
        gameOver.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        topPanel.add(gameOver);
        topPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.add(topPanel);
    }

    private void initializeBottomPanel(JPanel mainPanel, JPanel bottomPanel) {
        bottomPanel.setLayout(new BorderLayout());
        JLabel message = new JLabel("You reached the Building: "+ game.currentBuilding.getBuildingName(),JLabel.CENTER);
        JLabel message2 = new JLabel("",JLabel.CENTER);
        if(game.getGameState().isWon()) {
        	message2.setText("You won the game! You escaped from Koc!");
        }else {
        	message2.setText("You lost! You could not escape from Koc!");
        }
        message.setSize(300, 200);
        message2.setSize(300, 200);
        message.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        message2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        bottomPanel.add(message, BorderLayout.CENTER);
        bottomPanel.add(message2, BorderLayout.AFTER_LAST_LINE);
        bottomPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.add(bottomPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        mainPanel.setBackground(BACKGROUND_COLOR);
        System.out.println("Game ended");
    }

}