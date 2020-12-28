package id.ac.its.aff231yz160zlp118;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class MainMenu extends BasePanel {
    Button playButton = new Button(55, 100, 50, B_WIDTH, Color.darkGray, Color.white, "PLAY");
    Button creditButton = new Button(135, 100, 50, B_WIDTH, Color.darkGray, Color.white, "CREDITS");

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
        playButton.drawComponent(g);
        creditButton.drawComponent(g);
    }

    public ButtonClicked getButtonClicked(MouseEvent e) {
        if(playButton.isClicked(e))
            return ButtonClicked.PLAY;
        else if(creditButton.isClicked(e))
            return ButtonClicked.CREDITS;
        return ButtonClicked.NOT_CLICKED;
    }
}
