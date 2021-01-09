package id.ac.its.aff231yz160zlp118.snake;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class LevelSelector extends BasePanel {
    private static int banyakLevel;
    Button[] levelSelect;

    public LevelSelector() {
        super();
        banyakLevel = countLevel();
        levelSelect = new Button[banyakLevel];
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
        for(int i=0; i<banyakLevel; i++) {
            levelSelect[i] = new Button(55 + 80 * i, 100, 50, B_WIDTH,
                    "Level " + Integer.toString(i + 1));
            levelSelect[i].drawComponent(g);
        }
    }

    public static int getBanyakLevel() {
        return banyakLevel;
    }

    public int getLevelToPlay(MouseEvent e) {
        for(int i=0; i<getBanyakLevel(); i++)
            if(levelSelect[i].isClicked(e))
                return i;
        return -1;
    }
}
