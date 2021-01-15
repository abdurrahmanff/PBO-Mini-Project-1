package id.ac.its.aff231yz160zlp118.snake;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Snake extends JFrame {
    private Board gameBoard;
    private MainMenu mainMenu = new MainMenu(this);
    private HelpMenu helpMenu = new HelpMenu(this);
    private CreditMenu creditMenu = new CreditMenu(this);
    private LevelSelector levelSelector = new LevelSelector(this);
    private HighScoreStatsMenu highScoreStatsMenu = new HighScoreStatsMenu(this);
    private JScrollPane jScrollPane = new JScrollPane(levelSelector, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    private Snake() {
        initUI();
    }

    private void initUI() {
        mainMenu = new MainMenu(this);
        creditMenu.setVisible(false);
        levelSelector.setVisible(false);
        highScoreStatsMenu.setVisible(false);
        mainMenu.setVisible(true);
        add(mainMenu);

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Snake();
            ex.setVisible(true);
        });
    }

    public void playSnake(int level) {
        gameBoard = new Board(level, this);
        gameBoard.setVisible(true);
        this.add(gameBoard);
        pack();
        gameBoard.requestFocusInWindow();
    }

    public void closeGameBoard() {
        gameBoard.setVisible(false);
        getContentPane().remove(gameBoard);
    }

    public void openMainMenu() {
        mainMenu.setVisible(true);
        add(mainMenu);
    }

    public void closeMainMenu() {
        mainMenu.setVisible(false);
        getContentPane().remove(mainMenu);
    }

    public void closeLevelSelector() {
        jScrollPane.setVisible(false);
        remove(jScrollPane);
    }

    public void openLevelSelector() {
        levelSelector.setVisible(true);
        jScrollPane.setVisible(true);
        add(jScrollPane);
    }

    public void closeCreditMenu() {
        creditMenu.setVisible(false);
        getContentPane().remove(creditMenu);
    }

    public void openCreditMenu() {
        creditMenu.setVisible(true);
        add(creditMenu);
    }

    public void openHighScoreStats() {
        highScoreStatsMenu.setVisible(true);
        add(highScoreStatsMenu);
    }

    public void closeHighScoreStats() {
        highScoreStatsMenu.setVisible(false);
        getContentPane().remove(highScoreStatsMenu);
    }

    public void openHelpMenu() {
        helpMenu.setVisible(true);
        add(helpMenu);
    }

    public void closeHelpMenu() {
        helpMenu.setVisible(false);
        getContentPane().remove(helpMenu);
    }

    public void openReference() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI oURL = new URI("http://zetcode.com/javagames/snake/");
            desktop.browse(oURL);
        } catch (URISyntaxException | IOException exception) {
            exception.printStackTrace();
        }
    }
}
