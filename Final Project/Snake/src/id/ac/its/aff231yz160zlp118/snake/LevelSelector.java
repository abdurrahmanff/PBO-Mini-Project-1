package id.ac.its.aff231yz160zlp118.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class LevelSelector extends BasePanel {
    private static int banyakLevel;
    BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);

    public LevelSelector() {
        super();
        banyakLevel = countLevel();
        setPreferredSize(new Dimension(300, ( banyakLevel > 2 ? 282 + 80 * (banyakLevel - 2) : 282)));
        this.setLayout(layout);
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
    }

    public static int getBanyakLevel() {
        return banyakLevel;
    }
}
