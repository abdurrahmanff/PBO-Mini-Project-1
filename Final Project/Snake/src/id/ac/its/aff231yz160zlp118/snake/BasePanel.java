package id.ac.its.aff231yz160zlp118.snake;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public abstract class BasePanel extends JPanel {
    protected final int B_WIDTH = 300;
    protected final int B_HEIGHT = 300;

    public ButtonClicked getButtonClicked(MouseEvent e) {
        return ButtonClicked.NOT_CLICKED;
    }

    public BasePanel() {
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
    }
}
