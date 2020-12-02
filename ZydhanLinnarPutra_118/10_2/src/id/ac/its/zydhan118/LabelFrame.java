package id.ac.its.zydhan118;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class LabelFrame extends JFrame {
	private final JLabel jlabel;
	
	public LabelFrame() {
		super("Testing JLabel");
		setLayout(new FlowLayout());
		
		Icon bug = new ImageIcon(getClass().getResource("zydhan.jpg"));
		
		jlabel = new JLabel();
		jlabel.setText("<html>Zydhan Linnar Putra<br/>05111940000118</html>");
		jlabel.setIcon(bug);
		jlabel.setHorizontalTextPosition(SwingConstants.CENTER);
		jlabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		jlabel.setToolTipText("My Picture");
		add(jlabel);
	}
}
