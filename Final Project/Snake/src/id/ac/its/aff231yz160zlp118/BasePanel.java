package id.ac.its.aff231yz160zlp118;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class BasePanel extends JPanel {
    protected final int B_WIDTH = 300;
    protected final int B_HEIGHT = 300;

    public abstract ButtonClicked getButtonClicked(MouseEvent e);

    public BasePanel() {
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
    }
}
