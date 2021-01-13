package id.ac.its.aff231yz160zlp118.snake;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditMenu extends BasePanel implements ActionListener {
    JButton[] buttons = new JButton[2];

    public CreditMenu(Snake mainClass) {
        super(mainClass);
        buttons[0] = new JButton("MAIN MENU");
        buttons[1] = new JButton("REFERENCE");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(1, 140)));
        for(int i=0; i<buttons.length; i++) {
            buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            setButtonStyle(buttons[i]);
            buttons[i].addActionListener(this);
            add(buttons[i]);
            add(Box.createRigidArea(new Dimension(1, 20)));
        }
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
        String msg = "Created by :";
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2 - 100);
        String[] devName = {"Abdurrahman Fauzan F. 051-231", "Yusril Zubaydi 051-160", "Zydhan Linnar P. 051-118"};
        for(int i=0; i<3; i++)
            g.drawString(devName[i], (B_WIDTH - metr.stringWidth(devName[i])) / 2, B_HEIGHT / 2 - 90 + 20 * i + metr.getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "MAIN MENU") {
            mainClass.closeCreditMenu();
            mainClass.openMainMenu();
        } else if(e.getActionCommand() == "REFERENCE") {
            mainClass.openReference();
        }
    }
}
