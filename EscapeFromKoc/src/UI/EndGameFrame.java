package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Domain.Controller.GameController;


public class EndGameFrame extends JFrame{
    private static final Color BACKGROUND_COLOR = new Color(255, 0, 0);
    private static final long serialVersionUID = 1L;
    double score = GameController.getInstance().getPlayer().getScore();

    public EndGameFrame() {
        super("EndGame");
        setBounds(580, 350, 600, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(BACKGROUND_COLOR);
        JPanel mainPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        initializeMainPanel(mainPanel);
        initializeTopPanel(mainPanel, topPanel);
        initializeBottomPanel(mainPanel, bottomPanel);
        setVisible(true);
    }

    private void initializeMainPanel(JPanel mainPanel) {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);
    }

    private void initializeTopPanel(JPanel mainPanel, JPanel topPanel) {
        topPanel.setLayout(new GridBagLayout());
        JLabel gameOver = new JLabel("Game Over!");
        gameOver.setSize(1000, 200);
        gameOver.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        topPanel.add(gameOver);
        topPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.add(topPanel);
    }

    private void initializeBottomPanel(JPanel mainPanel, JPanel bottomPanel) {
        bottomPanel.setLayout(new GridBagLayout());
        JLabel message = new JLabel("Final score: "+ score);
        message.setSize(300, 200);
        message.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        bottomPanel.add(message);
        bottomPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.add(bottomPanel);
    }

}