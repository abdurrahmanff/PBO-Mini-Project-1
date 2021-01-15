package id.ac.its.aff231yz160zlp118.snake;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpMenu extends BasePanel implements ActionListener {
	JButton[] buttons = new JButton[2];

	public HelpMenu(Snake mainClass) {
		super(mainClass);
		buttons[0] = new JButton("PLAY");
		buttons[1] = new JButton("MAIN MENU");
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(1, 160)));
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
		String line1 = "There is Some Things You Must Know :";
		g.setColor(Color.WHITE);
		g.setFont(small);
		g.drawString(line1, (B_WIDTH - metr.stringWidth(line1)) / 2, B_HEIGHT / 2 - 100);
		String[] appletype = {"Red Apple : +1 Score and Length", "Golden Apple : +2 Score and Length", "Green Apple : -2 Score and Length", "Skull : Instant Death"};
		for(int i=0;i<4;i++) {
			g.drawString(appletype[i], (B_WIDTH - metr.stringWidth(appletype[i])) / 2, B_HEIGHT / 2 - 90 + 20 * i + metr.getHeight());
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		mainClass.closeHelpMenu();
		if(e.getActionCommand().equals("PLAY"))
			mainClass.openLevelSelector();
		else mainClass.openMainMenu();
	}

}
