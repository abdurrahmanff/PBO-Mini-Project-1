package id.ac.its.zydhan118;
import javax.swing.JOptionPane;

public class MainApp {

	public static void main(String[] args) {
		String[] inp = new String[3];
		inp[0] = JOptionPane.showInputDialog("Enter Triangle's a-side");
		inp[1] = JOptionPane.showInputDialog("Enter Triangle's b-side");
		inp[2] = JOptionPane.showInputDialog("Enter Triangle's c-side");

		Triangle triangle = new Triangle(Integer.parseInt(inp[0]),
				Integer.parseInt(inp[1]), Integer.parseInt(inp[2]));
		
		JOptionPane.showMessageDialog(null, 
				"The area of triangle is " + triangle.getArea() + 
				"\nThe circumference of triangle is " + triangle.getCircumference(), 
				"Triangle", JOptionPane.PLAIN_MESSAGE);
	}
}
