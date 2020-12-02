package id.ac.its.yusril160;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class LabelFrame extends JFrame {
	private final JLabel label;
	
	public LabelFrame() {
		super("Testing Jlabel");
		setLayout(new FlowLayout());
		Icon icon = new ImageIcon(getClass().getResource("1190339393.jpeg"));
		label = new JLabel();
		label.setText("Yusril Zubaydi 05111940000160");
		label.setIcon(icon);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setVerticalTextPosition(SwingConstants.BOTTOM);
		label.setToolTipText("This is Me");
		add(label);
		
	}
}
