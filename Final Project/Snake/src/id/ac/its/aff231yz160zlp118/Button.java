package id.ac.its.aff231yz160zlp118;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

// Rectangle with centered horizontal layout

public class Button extends JPanel {
    private final int yCenter;
    private final int width;
    private final int height;
    private final int windowWidth;
    private final Color buttonColor;
    private final String buttonMsg;
    private int xFrame;
    private int yFrame;
    Rectangle frame;

    public Button(int yCenter, int width, int height, int windowWidth, Color buttonColor, String buttonMsg) {
        this.yCenter = yCenter;
        this.width = width;
        this.height = height;
        this.windowWidth = windowWidth;
        this.buttonColor = buttonColor;
        this.buttonMsg = buttonMsg;
        this.xFrame = (windowWidth - width) / 2;
        this.yFrame = yCenter - height / 2;
        frame = new Rectangle(xFrame, yFrame, width, height);
    }

    public void drawComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        graphics2D.fill(frame);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(buttonMsg, xFrame + (width - metr.stringWidth(buttonMsg)) / 2, yCenter);
    }

    public boolean isClicked(MouseEvent e) {
        return e.getX() >= xFrame && e.getX() <= xFrame + width && e.getY() >= yFrame && e.getY() <= yFrame + height;
    }
}
