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

public class MainMenu extends BasePanel implements ActionListener {
    JButton[] buttons = new JButton[4];

    public MainMenu(Snake mainClass) {
        super(mainClass);
        buttons[0] = new JButton("PLAY");
        buttons[1] = new JButton("HELP");
        buttons[2] = new JButton("CREDITS");
        buttons[3] = new JButton("STATISTICS");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for(int i=0; i<buttons.length; i++) {
            add(Box.createRigidArea(new Dimension(1, 20)));
            buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            buttons[i].setBounds(98, 30 + 70*i, 100, 50);
            setButtonStyle(buttons[i]);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainClass.closeMainMenu();
        if(e.getActionCommand() == "PLAY")
            mainClass.openLevelSelector();
        else if(e.getActionCommand() == "CREDITS")
            mainClass.openCreditMenu();
        else if(e.getActionCommand() == "STATISTICS")
            mainClass.openHighScoreStats();
        else if(e.getActionCommand() == "HELP")
            mainClass.openHelpMenu();
    }
}
