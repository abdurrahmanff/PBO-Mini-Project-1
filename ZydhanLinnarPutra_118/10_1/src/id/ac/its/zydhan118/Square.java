package id.ac.its.zydhan118;

public class Square extends Rectangle {
	
	public Square() {
		super();
		System.out.println("[Square]: Default Constructor");
	}
	
	public Square(double side) {
//		this.height = side;
//		this.width = side;
		super(side, side);
		System.out.println("[Square]: Constructor 1");
		
	}
	
	public Square(String color, double side) {
		super(color, side, side);
		System.out.println("[Square]: Constructor 2");
	}
	
}
