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
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class LevelSelector extends BasePanel implements ActionListener {
    private static int banyakLevel;
    private JButton[] levelSelectRev2;

    public LevelSelector(Snake mainClass) {
        super(mainClass);
        banyakLevel = countLevel();
        setPreferredSize(new Dimension(300, ( banyakLevel > 2 ? 282 + 80 * (banyakLevel - 2) : 282)));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        drawLevelButton();
    }

    private int countLevel() {
        Scanner input = null;

        for(int i=0; ;i++) {
            try {
                input = new Scanner(Paths.get("assets/level/Level" + Integer.toString(i) + ".txt"));
            } catch (IOException ioException) {
                return i;
            }
            input.close();
        }
    }

    private void drawLevelButton() {
        levelSelectRev2 = new JButton[getBanyakLevel()];
        for(int i=0; i<getBanyakLevel(); i++) {
            add(Box.createRigidArea(new Dimension(1, 30)));
            levelSelectRev2[i] = new JButton("LEVEL " + Integer.toString(i + 1));
            levelSelectRev2[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            setButtonStyle(levelSelectRev2[i]);
            levelSelectRev2[i].addActionListener(this);
            add(levelSelectRev2[i]);
        }
        add(Box.createRigidArea(new Dimension(1, 30)));
        JButton mainMenuButton = new JButton("MAIN MENU");
        mainMenuButton.setMaximumSize(buttonDimension);
        mainMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        setButtonStyle(mainMenuButton);
        mainMenuButton.addActionListener(this);
        add(mainMenuButton);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
    }

    public static int getBanyakLevel() {
        return banyakLevel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainClass.closeLevelSelector();
        for(int i=0; i<getBanyakLevel(); i++) {
            if(e.getActionCommand().equals("LEVEL " + Integer.toString(i + 1))) {
                mainClass.playSnake(i);
                break;
            }
        }
        if(e.getActionCommand().equals("MAIN MENU"))
            mainClass.openMainMenu();
    }
}
