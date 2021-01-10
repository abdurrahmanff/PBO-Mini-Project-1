package id.ac.its.aff231yz160zlp118.snake;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;

public class Snake extends JFrame implements ActionListener {
    static BasePanel gameBoard;
    static BasePanel mainMenu = new MainMenu();
    static BasePanel creditMenu = new CreditMenu();
    static LevelSelector levelSelector = new LevelSelector();
    static BasePanel highScoreStats = new HighScoreStats();
    JScrollPane jScrollPane = new JScrollPane(levelSelector, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JButton[] levelSelectRev2;
    public Snake() {
        initUI();
    }

    private void initUI() {
        mainMenu = new MainMenu();
        creditMenu.setVisible(false);
        levelSelector.setVisible(false);
        highScoreStats.setVisible(false);
        levelSelectRev2 = new JButton[levelSelector.getBanyakLevel()];
        drawLevelButton();
        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
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

    public void drawLevelButton() {
        Dimension buttonDimension = new Dimension(new Dimension(100, 50));
        for(int i=0; i<levelSelector.getBanyakLevel(); i++) {
            levelSelector.add(Box.createRigidArea(new Dimension(1, 30)));
            levelSelectRev2[i] = new JButton("LEVEL " + Integer.toString(i + 1));
            levelSelectRev2[i].setMaximumSize(buttonDimension);
            levelSelectRev2[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            levelSelectRev2[i].addActionListener(this);
            levelSelectRev2[i].setBackground(Color.DARK_GRAY);
            levelSelectRev2[i].setForeground(Color.WHITE);
            levelSelectRev2[i].setFont(new Font("Helvetica", Font.BOLD, 14));
            levelSelectRev2[i].setBorder(null);
            levelSelector.add(levelSelectRev2[i]);
        }
        levelSelector.add(Box.createRigidArea(new Dimension(1, 30)));
        JButton mainMenuButton = new JButton("BACK");
        mainMenuButton.setMaximumSize(buttonDimension);
        mainMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainMenuButton.addActionListener(this);
        mainMenuButton.setBackground(Color.DARK_GRAY);
        mainMenuButton.setForeground(Color.WHITE);
        mainMenuButton.setBorder(null);
        mainMenuButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        levelSelector.add(mainMenuButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jScrollPane.setVisible(false);
        remove(jScrollPane);
        for(int i=0; i<levelSelector.getBanyakLevel(); i++) {
            if(e.getActionCommand().equals("LEVEL " + Integer.toString(i + 1))) {
                gameBoard = new Board(i);
                gameBoard.setVisible(true);
                this.add(gameBoard);
                pack();
                gameBoard.requestFocusInWindow();
                break;
            }
        }
        if(e.getActionCommand().equals("BACK")) {
            mainMenu.setVisible(true);
            add(mainMenu);
        }
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(mainMenu.isVisible()) {
                if(mainMenu.getButtonClicked(e) != ButtonClicked.NOT_CLICKED) {
                    mainMenu.setVisible(false);
                    getContentPane().remove(mainMenu);
                }
                if(mainMenu.getButtonClicked(e) == ButtonClicked.PLAY) {
                    levelSelector.setVisible(true);
                    jScrollPane.setVisible(true);
                    add(jScrollPane);
                } else if(mainMenu.getButtonClicked(e) == ButtonClicked.CREDITS) {
                    creditMenu.setVisible(true);
                    add(creditMenu);
                } else if(mainMenu.getButtonClicked(e) == ButtonClicked.HIGH_SCORE) {
                    highScoreStats.setVisible(true);
                    add(highScoreStats);
                }
            } else if(creditMenu.isVisible()) {
                if(creditMenu.getButtonClicked(e) == ButtonClicked.MAIN_MENU) {
                    creditMenu.setVisible(false);
                    getContentPane().remove(creditMenu);
                    mainMenu.setVisible(true);
                    add(mainMenu);
                } else if(creditMenu.getButtonClicked(e) == ButtonClicked.REFERENCE) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        URI oURL = new URI("http://zetcode.com/javagames/snake/");
                        desktop.browse(oURL);
                    } catch (URISyntaxException | IOException exception) {
                        exception.printStackTrace();
                    }
                }
            } else if(highScoreStats.isVisible() && highScoreStats.getButtonClicked(e) == ButtonClicked.MAIN_MENU) {
                highScoreStats.setVisible(false);
                getContentPane().remove(highScoreStats);
                mainMenu.setVisible(true);
                add(mainMenu);
            } else if(gameBoard.isVisible() && !((Board)gameBoard).isInGame()) {
                gameBoard.setVisible(false);
                getContentPane().remove(gameBoard);
                mainMenu.setVisible(true);
                add(mainMenu);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
