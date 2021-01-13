package id.ac.its.aff231yz160zlp118.snake;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.MouseEvent;

public abstract class BasePanel extends JPanel {
    protected final int B_WIDTH = 300;
    protected final int B_HEIGHT = 300;
    protected final Dimension buttonDimension = new Dimension(new Dimension(100, 50));
    protected final Snake mainClass;
    protected Font small = new Font("Helvetica", Font.BOLD, 14);
    protected FontMetrics metr = getFontMetrics(small);

    public BasePanel(Snake mainClass) {
        setBackground(Color.black);
        setFocusable(true);

        this.mainClass = mainClass;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
    }

    protected void setButtonStyle(JButton button) {
        button.setMaximumSize(buttonDimension);
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setFont(small);
        button.setBorder(null);
    }
}
