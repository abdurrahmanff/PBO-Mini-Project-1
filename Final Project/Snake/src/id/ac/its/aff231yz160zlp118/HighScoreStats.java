package id.ac.its.aff231yz160zlp118;

import java.awt.*;
import java.awt.event.MouseEvent;

public class HighScoreStats extends BasePanel {
    Button mainMenuButton = new Button(225, 100, 50, B_WIDTH, "MAIN MENU");

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);
        for(int i=0; i<LevelSelector.getBanyakLevel(); i++) {
            String tmp = String.format("Level " + Integer.toString(i + 1) + ": " +
                    ReadFromFile.readScore("score" + Integer.toString(i) + ".txt"));
            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(tmp, (B_WIDTH - metr.stringWidth(tmp)) / 2, B_HEIGHT / 2 - 60);
        }
        mainMenuButton.drawComponent(g);
    }

    @Override
    public ButtonClicked getButtonClicked(MouseEvent e) {
        if(mainMenuButton.isClicked(e))
            return ButtonClicked.MAIN_MENU;
        return ButtonClicked.NOT_CLICKED;
    }
}
