package id.ac.its.aff231yz160zlp118;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LevelSelector extends BasePanel {
    private final int banyakLevel = 1;
    Button[] levelSelect = new Button[banyakLevel];

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

    @Override
    public ButtonClicked getButtonClicked(MouseEvent e) {
        if(levelSelect[0].isClicked(e))
            return ButtonClicked.LEVEL_0;
        return ButtonClicked.NOT_CLICKED;
    }
}
