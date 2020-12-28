package id.ac.its.aff231yz160zlp118;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class CreditMenu extends BasePanel {
    Button mainMenuButton = new Button(135, 100, 50, B_WIDTH, "MAIN MENU");
    Button referenceButton = new Button(225, 100, 50, B_WIDTH, "REFERENCE");

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
        String msg = "Created by :";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2 - 100);
        String[] devName = {"Abdurrahman Fauzan F. 051-231", "Yusril Zubaydi 051-160", "Zydhan Linnar P. 051-118"};
        for(int i=0; i<3; i++)
            g.drawString(devName[i], (B_WIDTH - metr.stringWidth(devName[i])) / 2, B_HEIGHT / 2 - 90 + 20 * i + metr.getHeight());
        mainMenuButton.setyCenter(170);
        mainMenuButton.drawComponent(g);
        referenceButton.drawComponent(g);
    }

    public ButtonClicked getButtonClicked(MouseEvent e) {
        if(mainMenuButton.isClicked(e))
            return ButtonClicked.MAIN_MENU;
        else if(referenceButton.isClicked(e))
            return ButtonClicked.REFERENCE;
        return ButtonClicked.NOT_CLICKED;
    }
}
