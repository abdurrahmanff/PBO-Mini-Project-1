package id.ac.its.aff231yz160zlp118.snake;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighScoreStatsMenu extends BasePanel implements ActionListener {
    JButton mainMenuButton = new JButton("MAIN MENU");

    public HighScoreStatsMenu(Snake mainClass) {
        super(mainClass);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(1, B_HEIGHT / 2 - 60 + 20 * LevelSelector.getBanyakLevel())));
        setButtonStyle(mainMenuButton);
        mainMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainMenuButton.setSize(buttonDimension);
        mainMenuButton.addActionListener(this);
        add(mainMenuButton);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
        HighScore highScore = LoadFromFile.loadScore();
        for(int i=0; i<LevelSelector.getBanyakLevel(); i++) {
            String tmp = String.format("Level " + Integer.toString(i + 1) + ": " +
                    highScore.getHighScore(i));
            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(tmp, (B_WIDTH - metr.stringWidth(tmp)) / 2, B_HEIGHT / 2 - 60 + 20 * i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainClass.closeHighScoreStats();
        mainClass.openMainMenu();
    }
}
