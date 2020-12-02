package id.ac.its.yusril160;
import javax.swing.JOptionPane;

public class MainApp {

	public static void main(String[] args) {
		String input = JOptionPane.showInputDialog("Enter Square's Side");
		Square s = new Square(Double.parseDouble(input));
		JOptionPane.showMessageDialog(null, 
				"Area of the Square is " + s.getArea() + 
				"\nCircumference of The Square is " + s.getCircumference(),
				"Square", JOptionPane.PLAIN_MESSAGE);
	}

}
