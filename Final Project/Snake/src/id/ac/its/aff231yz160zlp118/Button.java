package id.ac.its.aff231yz160zlp118;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

// Rectangle with centered horizontal layout

public class Button extends JPanel {
    private int yCenter;
    private final int width;
    private final int height;
    private final int windowWidth;
    private final Color buttonColor;
    private final Color fontColor;
    private final String buttonMsg;
    private int xFrame;
    private int yFrame;
    Rectangle frame;

    public Button(int yCenter, int width, int height, int windowWidth, Color buttonColor, Color fontColor, String buttonMsg) {
        this.yCenter = yCenter;
        this.width = width;
        this.height = height;
        this.windowWidth = windowWidth;
        this.buttonColor = buttonColor;
        this.fontColor = fontColor;
        this.buttonMsg = buttonMsg;
        this.xFrame = (windowWidth - width) / 2;
        this.yFrame = yCenter - height / 2;
    }

    public void drawComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        frame = new Rectangle(xFrame, yFrame, width, height);
        graphics2D.setColor(buttonColor);
        graphics2D.fill(frame);

        g.setColor(fontColor);
        g.setFont(small);
        g.drawString(buttonMsg, xFrame + (width - metr.stringWidth(buttonMsg)) / 2, yCenter + metr.getHeight() / 4);
    }

    public boolean isClicked(MouseEvent e) {
        int x = e.getX() - 7;
        int y = e.getY() - 30;
        return x >= xFrame && x <= xFrame + width && y >= yFrame && y <= yFrame + height;
    }

    public void setyCenter(int yCenter) {
        this.yCenter = yCenter;
        this.yFrame = yCenter - height / 2;
    }
}
