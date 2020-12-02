package id.ac.its.fauzan231;
import javax.swing.JOptionPane;

public class MainApp {

	public static void main(String[] args) {
		String inputanString =
				JOptionPane.showInputDialog("Enter Circle's radius");
		
		Circle circ = new Circle (Double.parseDouble(inputanString));
		
		JOptionPane.showMessageDialog(null, 
				"The area of circle is " + circ.getArea() + 
				"\nThe circumference of circle is " + circ.getCircumference(), 
				"Circle", JOptionPane.PLAIN_MESSAGE);
	}
}
